package com.pe.gidtec.servicedesk.ticket.tickets.model.entity;

import com.pe.gidtec.servicedesk.ticket.audit.entity.AuditEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TicketEntityWithLocalDateData {

    @Id
    private String ticketId;

    @Field(name = "created_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;
}
