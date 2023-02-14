package com.pe.gidtec.servicedesk.ticket.messages.dao.impl;

import com.pe.gidtec.servicedesk.ticket.messages.dao.MessagesResponseDao;
import com.pe.gidtec.servicedesk.ticket.messages.model.entity.MessageResponseEntity;
import com.pe.gidtec.servicedesk.ticket.messages.repository.MessagesResponseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageResponseDaoImpl implements MessagesResponseDao {

    private final MessagesResponseRepository messagesResponseRepository;

    @Override
    public Mono<MessageResponseEntity> findMessageById(String messageId) {
        return messagesResponseRepository.findMessageResponseEntityByMessageIdAndAuditDeletedFalse(messageId);
    }

    @Override
    public Mono<MessageResponseEntity> saveMessageResponse(MessageResponseEntity entity) {
        return messagesResponseRepository.save(entity)
                .doOnSuccess(message -> log.info("Mensaje de respuesta " + message.toString() + " guardado con éxito."))
                .onErrorResume( ex -> Mono.error(new Exception("Ocurrio un error al intentar guardar el mensaje de respuesta")));
    }

    @Override
    public Mono<Boolean> verifyExistsMessageById(String messageId) {
        return messagesResponseRepository.existsMessageResponseEntityByMessageId(messageId);
    }

    @Override
    public Flux<MessageResponseEntity> findMessagesByTicket(String ticketId) {
        return messagesResponseRepository.findAllByTicketIdAndAuditDeletedFalseOrderByAuditCreatedDateAsc(ticketId);
    }

}
