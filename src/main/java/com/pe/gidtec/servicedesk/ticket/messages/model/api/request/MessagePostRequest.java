package com.pe.gidtec.servicedesk.ticket.messages.model.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "MessagePostRequest")
public class MessagePostRequest {

    @NotBlank(message = "El valor de 'userSenderId' no puede ser vacio.")
    @Schema(name = "userSenderId",
            description = "El identificador del usuario que genera un mensaje.",
            example = "63c49aefb24fdd6906a50231")
    private String userSenderId;

    @NotBlank(message = "El valor de 'ticketId' no puede ser vacio.")
    @Schema(name = "ticketId",
            description = "El identificador del ticket al que se genera un mensaje.",
            example = "63c49aefb24fdd6906a50232")
    private String ticketId;

    @Schema(name = "messageParentId",
            description = "El identificador del mensaje padre, el cual se está respondiendo",
            example = "63c49aefb24fdd6906a50230", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String messageParentId;

    @NotBlank(message = "El valor de 'messageResponse' no puede ser vacio.")
    @Schema(name = "messageResponse",
            description = "El mensaje que se envia al responder un ticket",
            example = "Su solicitud sera derivada a otra area.")
    private String messageResponse;
}
