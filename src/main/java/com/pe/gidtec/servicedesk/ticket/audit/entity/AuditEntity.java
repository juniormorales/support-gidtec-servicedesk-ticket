package com.pe.gidtec.servicedesk.ticket.audit.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AuditEntity {

    @Field(name = "status_code")
    private String statusCode;

    @Field(name = "deleted")
    private Boolean deleted;

    @Field(name = "created_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;

    @Field(name = "last_modified_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastModifiedDate;

    @Field(name = "created_by")
    private String createdBy;

    @Field(name = "last_modified_by")
    private String lastModifiedBy;
}
