package com.pe.gidtec.servicedesk.ticket.tickets.dao;

import com.pe.gidtec.servicedesk.ticket.tickets.model.entity.TicketEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface TicketsDao {

    Mono<TicketEntity> saveTicket(TicketEntity entity);

    Mono<TicketEntity> getTicketById(String ticketId);

    Flux<TicketEntity> findAll();

    Flux<TicketEntity> findAllByUserAssignedId(String userAssignedId);

    Flux<TicketEntity> findAllByTypeCode(String typeCode);

    Flux<TicketEntity> findAllByStatusCode(String statusCode);

    Mono<Long> totalTicketsByTypeCode(String typeCode);

    Mono<Long> totalTicketsByStatusCode(String statusCode);

    Mono<Boolean> verifyExistsTicketById(String ticketId);

    Flux<TicketEntity> findAllByStartDateAndEndDate(LocalDateTime startDate, LocalDateTime endDate);

    Flux<TicketEntity> findAllByStartDateAndEndDateAndUser(LocalDateTime startDate, LocalDateTime endDate, String userId);

}
