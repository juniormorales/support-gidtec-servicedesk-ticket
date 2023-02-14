package com.pe.gidtec.servicedesk.ticket.questionnaire.model.api.request;

import com.pe.gidtec.servicedesk.lib.util.Patterns;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "QuestionnairePostRequest")
public class QuestionnairePostRequest {

    @Schema(name = "questionnaireId",
            description = "Identificador de la encuesta a llenar datos",
            example = "63c49aefb24fdd6906a50232")
    @NotBlank(message = "El valor de 'questionnaireId' no debe estar vacio.")
    private String questionnaireId;

    @Schema(name = "problemOnTime",
            description = "Respuesta a la pregunta de problema resuelto a tiempo",
            example = "true", pattern = "^(true|false)$")
    @NotBlank(message = "El valor de 'problemOnTime' no debe estar vacio.")
    @Pattern(regexp = "^(true|false)$", message = "El valor de 'problemOnTime' solo acepta 'true' o 'false'")
    private String problemOnTime;

    @Schema(name = "problemSolved",
            description = "Respuesta a la pregunta de problema resuelto con exito",
            example = "true", pattern = "^(true|false)$")
    @NotBlank(message = "El valor de 'problemSolved' no debe estar vacio.")
    @Pattern(regexp = "^(true|false)$", message = "El valor de 'problemSolved' solo acepta 'true' o 'false'")
    private String problemSolved;

    @Schema(name = "rating",
            description = "Puntaje de evaluacion del 1 al 5",
            example = "5", pattern = Patterns.NUMERIC)
    @NotBlank(message = "El valor de 'rating' no debe estar vacio.")
    @Pattern(regexp = Patterns.NUMERIC, message = "El valor de 'rating' debe ser numerico")
    private String rating;
}
