package com.pe.gidtec.servicedesk.ticket.user.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "users")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEntity {

    @Id
    private String userId;

    @Field(name = "names")
    private String names;

    @Field(name = "last_names")
    private String lastNames;

    @Field(name = "company_name")
    private String companyName;

    @Field(name = "phone_number")
    private String phoneNumber;

    @Field(name = "web_site")
    private String webSite;

    @Field(name = "email")
    private String email;
}
