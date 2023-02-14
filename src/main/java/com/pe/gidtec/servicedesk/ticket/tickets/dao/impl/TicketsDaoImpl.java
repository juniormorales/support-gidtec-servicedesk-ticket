package com.pe.gidtec.servicedesk.ticket.tickets.dao.impl;

import com.pe.gidtec.servicedesk.ticket.tickets.dao.TicketsDao;
import com.pe.gidtec.servicedesk.ticket.tickets.model.entity.TicketEntity;
import com.pe.gidtec.servicedesk.ticket.tickets.repository.TicketsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class TicketsDaoImpl implements TicketsDao {

    private final TicketsRepository ticketsRepository;

    @Override
    public Mono<TicketEntity> saveTicket(TicketEntity entity) {
        return ticketsRepository.save(entity)
                .doOnSuccess(message -> log.info("Ticket " + message.toString() + " guardado con éxito."))
                .onErrorResume( ex -> Mono.error(new Exception("Ocurrio un error al intentar guardar el ticket")));
    }

    @Override
    public Mono<TicketEntity> getTicketById(String ticketId) {
        return ticketsRepository.findTicketEntityByTicketIdAndAuditDeletedFalse(ticketId);
    }

    @Override
    public Flux<TicketEntity> findAll() {
        return ticketsRepository.findAllByAuditDeletedFalseOrderByAuditCreatedDateDesc();
    }

    @Override
    public Flux<TicketEntity> findAllByUserAssignedId(String userAssignedId) {
        return ticketsRepository.findAllByUserAssignedIdAndAuditDeletedFalseOrderByAuditCreatedDateDesc(userAssignedId);
    }

    @Override
    public Flux<TicketEntity> findAllByTypeCode(String typeCode) {
        return ticketsRepository.findAllByTypeCodeAndAuditDeletedFalseOrderByAuditCreatedDateDesc(typeCode);
    }

    @Override
    public Flux<TicketEntity> findAllByStatusCode(String statusCode) {
        return ticketsRepository.findAllByStatusCodeAndAuditDeletedFalseOrderByAuditCreatedDateDesc(statusCode);
    }

    @Override
    public Mono<Long> totalTicketsByTypeCode(String typeCode) {
        return ticketsRepository.countAllByTypeCodeAndAuditDeletedFalseAndStatusCode(typeCode,"AB");
    }

    @Override
    public Mono<Long> totalTicketsByStatusCode(String statusCode) {
        return ticketsRepository.countAllByStatusCodeAndAuditDeletedFalse(statusCode);
    }

    @Override
    public Mono<Boolean> verifyExistsTicketById(String ticketId) {
        return ticketsRepository.existsTicketEntityByTicketIdAndAuditDeletedFalse(ticketId);
    }

    @Override
    public Flux<TicketEntity> findAllByStartDateAndEndDate(LocalDateTime startDate, LocalDateTime endDate) {
        return ticketsRepository.findAllByAuditDeletedFalseAndAuditCreatedDateBetweenOrderByAuditCreatedDateAsc(startDate,endDate);
    }

    @Override
    public Flux<TicketEntity> findAllByStartDateAndEndDateAndUser(LocalDateTime startDate, LocalDateTime endDate, String userId) {
        return ticketsRepository.findAllByAuditDeletedFalseAndUserCreatorIdAndAuditCreatedDateBetweenOrderByAuditCreatedDateAsc(userId,startDate,endDate);
    }
}
