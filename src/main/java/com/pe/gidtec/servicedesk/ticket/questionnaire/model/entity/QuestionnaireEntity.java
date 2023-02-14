package com.pe.gidtec.servicedesk.ticket.questionnaire.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "questionnaires")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionnaireEntity {

    @Id
    private String questionnaireId;

    @Field(name = "ticket_id")
    private String ticketId;

    @Field(name = "problem_on_time")
    private Boolean problemOnTime;

    @Field(name = "problem_solved")
    private Boolean problemSolved;

    @Field(name = "rating")
    private Integer rating;

    @Field(name = "date_created")
    private LocalDateTime dateCreated;

    @Field(name = "user_surveyed")
    private String userSurveyedId;

    @Field(name = "status_code")
    private String statusCode;

    @Field(name = "date_completed")
    private LocalDateTime dateCompleted;

}
