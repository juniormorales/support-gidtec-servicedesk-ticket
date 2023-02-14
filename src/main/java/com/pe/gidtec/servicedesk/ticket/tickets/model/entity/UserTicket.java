package com.pe.gidtec.servicedesk.ticket.tickets.model.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserTicket {

    @Field(name = "id")
    private String id;

    @Field(name = "names")
    private String names;

    @Field(name = "last_names")
    private String lastNames;

    @Field(name = "company_name")
    private String companyName;
}
