package com.pe.gidtec.servicedesk.ticket.messages.service.impl;

import com.pe.gidtec.servicedesk.ticket.audit.entity.AuditEntity;
import com.pe.gidtec.servicedesk.ticket.common.model.api.response.ResultResponse;
import com.pe.gidtec.servicedesk.ticket.common.model.header.CommonHeaders;
import com.pe.gidtec.servicedesk.ticket.common.util.ResponseStatus;
import com.pe.gidtec.servicedesk.ticket.messages.dao.MessagesResponseDao;
import com.pe.gidtec.servicedesk.ticket.messages.mapper.MessageResponseMapper;
import com.pe.gidtec.servicedesk.ticket.messages.model.api.request.MessagePostRequest;
import com.pe.gidtec.servicedesk.ticket.messages.model.api.request.MessagePutRequest;
import com.pe.gidtec.servicedesk.ticket.messages.model.api.response.MessageGetResponse;
import com.pe.gidtec.servicedesk.ticket.messages.model.api.response.TotalMessageResponse;
import com.pe.gidtec.servicedesk.ticket.messages.model.entity.MessageResponseEntity;
import com.pe.gidtec.servicedesk.ticket.messages.service.MessagesResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessagesResponseServiceImpl implements MessagesResponseService {

    private final MessagesResponseDao messagesResponseDao;

    private final MessageResponseMapper messageResponseMapper;

    @Override
    public Mono<ResultResponse<TotalMessageResponse>> findMessagesInTreeByTicket(String ticketId) {
       return messagesResponseDao.findMessagesByTicket(ticketId)
               .collectList()
               .switchIfEmpty(Mono.just(Collections.emptyList()))
               .map(this::buildTotalMessagesByListMessages)
               .map(response -> {
                   if(Objects.isNull(response.getMessageId())) {
                      return ResultResponse.ok(null);
                   } else {
                      return ResultResponse.ok(response);
                   }
               });

    }

    @Override
    public Mono<ResultResponse<List<TotalMessageResponse>>> findMessagesInListByTicket(String ticketId) {
        return messagesResponseDao.findMessagesByTicket(ticketId)
                .map(messageResponseMapper::entityToTotalMessageResponseWhitOutChild)
                .collectList()
                .map(response -> {
                    if(response.isEmpty()) {
                        return ResultResponse.ok(new ArrayList<>());
                    } else {
                        return ResultResponse.ok(response);
                    }
                });
    }

    @Override
    public Mono<ResultResponse<MessageGetResponse>> createMessageResponse(MessagePostRequest request, CommonHeaders headers) {
        return messagesResponseDao.saveMessageResponse(messageResponseMapper.messagePostRequestToEntity(request, headers))
                .map(response -> ResultResponse.ok(messageResponseMapper.entityToMessageGetResponse((response))))
                .onErrorReturn(ResultResponse.error(ResponseStatus.ERROR_SAVE_TICKET));
    }

    @Override
    public Mono<ResultResponse<MessageGetResponse>> updateMessageResponse(MessagePutRequest request, CommonHeaders headers) {
        log.info("updateMessageResponse -> {}", request );
        return messagesResponseDao.verifyExistsMessageById(request.getMessageId())
                .flatMap(bool -> {
                    if (Boolean.TRUE.equals(bool)) {
                        return messagesResponseDao.findMessageById(request.getMessageId())
                                .map(entity -> buildRequestForUpdate(entity, request, headers))
                                .flatMap(messagesResponseDao::saveMessageResponse)
                                .map(response -> ResultResponse.ok(messageResponseMapper.entityToMessageGetResponse(response)))
                                .onErrorReturn(ResultResponse.error(ResponseStatus.ERROR_SAVE_MESSAGE));
                    } else {
                        return Mono.just(ResultResponse.error(ResponseStatus.BAD_ID_MESSAGE_NOT_EXISTS));
                    }
                });
    }

    private MessageResponseEntity buildRequestForUpdate(MessageResponseEntity entity,MessagePutRequest request, CommonHeaders headers) {
        entity.setMessageResponse(request.getMessageResponse());
        entity.getAudit().setLastModifiedBy(headers.getUserCode());
        entity.getAudit().setLastModifiedDate(LocalDateTime.now());
        return entity;
    }


    private TotalMessageResponse buildTotalMessagesByListMessages(List<MessageResponseEntity> entities) {
        log.info("buildTotalMessagesByListMessages -> {}", entities );
        if(entities.isEmpty()) {
            return TotalMessageResponse.builder().build();
        }
        MessageResponseEntity messageEntity = entities.stream()
                .filter(message -> Objects.isNull(message.getMessageParentId()))
                .findAny()
                .orElse(MessageResponseEntity.builder()
                        .audit(AuditEntity.builder().build())
                        .build());
        log.info("messageEntity -> {}", messageEntity);
        TotalMessageResponse total = messageResponseMapper.entityToTotalMessageResponseWhitOutChild(messageEntity);
        entities = entities.stream().filter(message -> !Objects.isNull(message.getMessageParentId())).collect(Collectors.toList());

       return recursiveParentToChild(entities,total);
    }

    private TotalMessageResponse recursiveParentToChild(List<MessageResponseEntity> children, TotalMessageResponse parent) {
        log.info("recursiveParentToChild -> {}", parent );
        if(children.isEmpty()) {
            return parent;
        }
        for(MessageResponseEntity m: children) {
            if(m.getMessageParentId().equals(parent.getMessageId())) {
                TotalMessageResponse child = messageResponseMapper.entityToTotalMessageResponseWhitOutChild(m);
                children.remove(m);
                parent.setMessageChildResponse(recursiveParentToChild(children,child));
                return parent;
            }
        }
        return null;
    }
}
