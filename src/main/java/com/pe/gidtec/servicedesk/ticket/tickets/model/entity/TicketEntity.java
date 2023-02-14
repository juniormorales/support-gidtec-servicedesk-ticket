package com.pe.gidtec.servicedesk.ticket.tickets.model.entity;

import com.pe.gidtec.servicedesk.ticket.audit.entity.AuditEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "tickets")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TicketEntity {

    @Id
    private String ticketId;

    @Field(name = "user_creator")
    private UserTicket userCreator;

    @Field(name = "user_assigned")
    private UserTicket userAssigned;

    @Field(name = "type_code")
    private String typeCode;

    @Field(name = "category_code")
    private String categoryCode;

    @Field(name = "status_code")
    private String statusCode;

    @Field(name = "issue")
    private String issue;

    @Field(name = "instance")
    private String instance;

    @Field(name = "duration")
    private Integer duration;

    private AuditEntity audit;
}
