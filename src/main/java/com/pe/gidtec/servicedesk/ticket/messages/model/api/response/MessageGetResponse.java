package com.pe.gidtec.servicedesk.ticket.messages.model.api.response;

import com.pe.gidtec.servicedesk.ticket.audit.response.Audit;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "MessageGetResponse")
public class MessageGetResponse {

    @Schema(name = "messageId",
            description = "El identificador del mensaje respuesta.",
            example = "63c49aefb24fdd6906a50232")
    private String messageId;

    @Schema(name = "userSenderId",
            description = "El identificador del usuario que genera un mensaje.",
            example = "63c49aefb24fdd6906a50231")
    private String userSenderId;

    @Schema(name = "messageParentId",
            description = "El identificador del mensaje al cual se está respondiendo",
            example = "63c49aefb24fdd6906a50230")
    private String messageParentId;

    @Schema(name = "messageResponse",
            description = "El mensaje que se envia al responder un ticket",
            example = "Su solicitud sera derivada a otra area.")
    private String messageResponse;

    @Schema(name = "audit",
            description = "Objeto de auditoria")
    private Audit audit;
}
