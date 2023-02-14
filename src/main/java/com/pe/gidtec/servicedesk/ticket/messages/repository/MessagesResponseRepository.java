package com.pe.gidtec.servicedesk.ticket.messages.repository;

import com.pe.gidtec.servicedesk.ticket.messages.model.entity.MessageResponseEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MessagesResponseRepository extends ReactiveMongoRepository<MessageResponseEntity, String> {

    Flux<MessageResponseEntity> findAllByTicketIdAndAuditDeletedFalseOrderByAuditCreatedDateAsc(String ticketId);

    Mono<MessageResponseEntity> findMessageResponseEntityByMessageIdAndAuditDeletedFalse(String messageId);

    Mono<Boolean> existsMessageResponseEntityByMessageId(String messageId);
}
