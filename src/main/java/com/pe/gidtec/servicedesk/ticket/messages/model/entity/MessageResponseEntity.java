package com.pe.gidtec.servicedesk.ticket.messages.model.entity;

import com.pe.gidtec.servicedesk.ticket.audit.entity.AuditEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "messages")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageResponseEntity {

    @Id
    private String messageId;

    @Field(name = "user_sender_id")
    private String userSenderId;

    @Field(name = "ticket_id")
    private String ticketId;

    @Field(name = "message_parent_id")
    private String messageParentId;

    @Field(name = "message_response")
    private String messageResponse;

    @Field(name = "audit")
    AuditEntity audit;
}
