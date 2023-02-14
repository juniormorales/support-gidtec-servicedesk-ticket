package com.pe.gidtec.servicedesk.ticket.messages.dao;

import com.pe.gidtec.servicedesk.ticket.messages.model.entity.MessageResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MessagesResponseDao {

    Mono<MessageResponseEntity> findMessageById(String messageId);

    Mono<MessageResponseEntity> saveMessageResponse(MessageResponseEntity entity);

    Mono<Boolean> verifyExistsMessageById(String messageId);

    Flux<MessageResponseEntity> findMessagesByTicket(String ticketId);

}
