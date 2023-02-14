package com.pe.gidtec.servicedesk.ticket.common.util;

import com.pe.gidtec.servicedesk.ticket.config.AuditProperties;
import com.pe.gidtec.servicedesk.ticket.config.TicketProperties;
import com.pe.gidtec.servicedesk.ticket.historical.model.entity.HistoricalEntity;
import com.pe.gidtec.servicedesk.ticket.messages.model.api.response.TotalMessageResponse;
import com.pe.gidtec.servicedesk.ticket.messages.model.entity.MessageResponseEntity;
import com.pe.gidtec.servicedesk.ticket.tickets.model.entity.TicketEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class MappingHelper {

    private final AuditProperties auditProperties;

    private final TicketProperties ticketProperties;

    @Named("getAuditStatusDescriptionFromMessages")
    public String getAuditStatusDescriptionFromMessages(MessageResponseEntity entity) {
        log.info("MessageResponseEntity -> {}", entity);
        return auditProperties.getStatusCode().get(entity.getAudit().getStatusCode());
    }

    @Named("getAuditStatusDescriptionFromTicket")
    public String getAuditStatusDescriptionFromTicket(TicketEntity entity) {
        log.info("TicketEntity -> {}", entity);
        return auditProperties.getStatusCode().get(entity.getAudit().getStatusCode());
    }

    @Named("getAuditStatusDescriptionFromHistorical")
    public String getAuditStatusDescriptionFromHistorical(HistoricalEntity entity) {
        log.info("HistoricalEntity -> {}", entity);
        return auditProperties.getStatusCode().get(entity.getAudit().getStatusCode());
    }

    @Named("getTicketTypeDescriptionFromTicket")
    public String getTicketTypeDescriptionFromTicket(TicketEntity entity) {
        log.info("TicketEntity -> {}", entity);
        return ticketProperties.getTypeCode().get(entity.getTypeCode());
    }


    @Named("getTicketCategoryDescriptionFromTicket")
    public String getTicketCategoryDescriptionFromTicket(TicketEntity entity) {
        log.info("TicketEntity -> {}", entity);
        return ticketProperties.getCategoryCode().get(entity.getCategoryCode());
    }


    @Named("getTicketStatusDescriptionFromTicket")
    public String getTicketStatusDescriptionFromTicket(TicketEntity entity) {
        log.info("TicketEntity -> {}", entity);
        return ticketProperties.getStatusCode().get(entity.getStatusCode());
    }

    @Named("getDueDate")
    public String getDueDate(TicketEntity entity){
        log.info("TicketEntity -> {}", entity);
        return entity.getAudit().getCreatedDate().plusHours(entity.getDuration()).toString();
    }


}
