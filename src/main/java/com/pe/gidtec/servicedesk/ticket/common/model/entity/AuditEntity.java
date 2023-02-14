package com.pe.gidtec.servicedesk.ticket.common.model.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditEntity {

    @Field(name = "statusCode")
    private String statusCode;

    @Field(name = "deleted")
    private Boolean deleted;

    @Field(name = "createdDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;

    @Field(name = "lastModifiedDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastModifiedDate;

    @Field(name = "createdBy")
    private String createdBy;

    @Field(name = "lastModifiedBy")
    private String lastModifiedBy;
}
