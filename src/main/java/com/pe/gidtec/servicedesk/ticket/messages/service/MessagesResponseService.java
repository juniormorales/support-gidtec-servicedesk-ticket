package com.pe.gidtec.servicedesk.ticket.messages.service;

import com.pe.gidtec.servicedesk.ticket.common.model.api.response.ResultResponse;
import com.pe.gidtec.servicedesk.ticket.common.model.header.CommonHeaders;
import com.pe.gidtec.servicedesk.ticket.messages.model.api.request.MessagePostRequest;
import com.pe.gidtec.servicedesk.ticket.messages.model.api.request.MessagePutRequest;
import com.pe.gidtec.servicedesk.ticket.messages.model.api.response.MessageGetResponse;
import com.pe.gidtec.servicedesk.ticket.messages.model.api.response.TotalMessageResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MessagesResponseService {

    Mono<ResultResponse<TotalMessageResponse>> findMessagesInTreeByTicket(String ticketId);

    Mono<ResultResponse<List<TotalMessageResponse>>> findMessagesInListByTicket(String ticketId);

    Mono<ResultResponse<MessageGetResponse>> createMessageResponse(MessagePostRequest request, CommonHeaders headers);

    Mono<ResultResponse<MessageGetResponse>>updateMessageResponse(MessagePutRequest request, CommonHeaders headers);

}
