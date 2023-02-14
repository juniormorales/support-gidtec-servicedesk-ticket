package com.pe.gidtec.servicedesk.ticket.historical.model.entity;

import com.pe.gidtec.servicedesk.ticket.audit.entity.AuditEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "historicals")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HistoricalEntity {

    @Id
    private String historicalId;

    @Field(name = "user_assigned_id")
    private String userAssignedId;

    @Field(name = "user_reassigned_id")
    private String userReassignedId;

    @Field(name = "ticket_id")
    private String ticketId;

    @Field(name = "reason")
    private String reason;

    @Field(name = "audit")
    AuditEntity audit;
}
