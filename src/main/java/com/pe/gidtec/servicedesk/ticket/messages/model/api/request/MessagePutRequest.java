package com.pe.gidtec.servicedesk.ticket.messages.model.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "MessagePutRequest")
public class MessagePutRequest {

    @NotBlank(message = "El valor de 'messageId' no puede ser vacio.")
    @Schema(name = "messageId",
            description = "El identificador del mensaje.",
            example = "63c49aefb24fdd6906a50233")
    private String messageId;

    @NotBlank(message = "El valor de 'messageResponse' no puede ser vacio.")
    @Schema(name = "messageResponse",
            description = "El mensaje que se envia al responder un ticket",
            example = "Su solicitud sera derivada a otra area.")
    private String messageResponse;
}
