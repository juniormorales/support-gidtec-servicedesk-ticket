package com.pe.gidtec.servicedesk.ticket.questionnaire.model.api.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "QuestionnaireGetResponse")
public class QuestionnaireGetResponse {

    @Schema(name = "questionnaireId",
            description = "Identificador de la encuesta",
            example = "63c49aefb24fdd6906a50231")
    private String questionnaireId;

    @Schema(name = "ticketId",
            description = "Identificador del ticket donde pertenece la encuesta",
            example = "63c49aefb24fdd6906a50232")
    private String ticketId;

    @Schema(name = "problemOnTime",
            description = "Pregunta de problema resuelto a tiempo")
    private Question problemOnTime;

    @Schema(name = "problemOnTime",
            description = "Pregunta de problema resuelto con exito")
    private Question problemSolved;

    @Schema(name = "rating",
            description = "Pregunta de evaluacion del 1 al 5")
    private Question rating;

    @Schema(name = "dateCreated",
            description = "Hora de creación de la encuesta",
            example = "2023-01-26T21:46:00Z")
    private String dateCreated;

    @Schema(name = "dateCompleted",
            description = "Hora de llenado de la encuesta",
            example = "2023-01-26T21:46:00Z")
    private String dateCompleted;

    @Schema(name = "status",
            description = "Estado de la encuesta",
            example = "Pendiente")
    private String status;
}
