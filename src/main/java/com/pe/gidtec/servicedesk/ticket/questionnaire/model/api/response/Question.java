package com.pe.gidtec.servicedesk.ticket.questionnaire.model.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Question")
public class Question {

    @Schema(name = "question",
            description = "Pregunta hecha al usuario")
    private String question;


    @Schema(name = "question",
            description = "Respuesta a la pregunta")
    private String response;
}
