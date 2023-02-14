package com.pe.gidtec.servicedesk.ticket.tickets.repository;

import com.pe.gidtec.servicedesk.ticket.tickets.model.entity.TicketEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface TicketsRepository extends ReactiveMongoRepository<TicketEntity,String> {

    Flux<TicketEntity> findAllByAuditDeletedFalseOrderByAuditCreatedDateDesc();

    Mono<TicketEntity> findTicketEntityByTicketIdAndAuditDeletedFalse(String ticketId);

    Flux<TicketEntity> findAllByUserAssignedIdAndAuditDeletedFalseOrderByAuditCreatedDateDesc(String userAssignedId);

    Flux<TicketEntity> findAllByTypeCodeAndAuditDeletedFalseOrderByAuditCreatedDateDesc(String typeTicketCode);

    Flux<TicketEntity> findAllByStatusCodeAndAuditDeletedFalseOrderByAuditCreatedDateDesc(String statusCode);

    Mono<Long> countAllByTypeCodeAndAuditDeletedFalseAndStatusCode(String typeTicketCode, String statusCode);

    Mono<Long> countAllByStatusCodeAndAuditDeletedFalse(String statusCode);

    Mono<Boolean> existsTicketEntityByTicketIdAndAuditDeletedFalse(String ticketId);

    Flux<TicketEntity> findAllByAuditDeletedFalseAndAuditCreatedDateBetweenOrderByAuditCreatedDateAsc(LocalDateTime startDate, LocalDateTime endDate);

    Flux<TicketEntity> findAllByAuditDeletedFalseAndUserCreatorIdAndAuditCreatedDateBetweenOrderByAuditCreatedDateAsc(String userId, LocalDateTime startDate, LocalDateTime endDate);

}
