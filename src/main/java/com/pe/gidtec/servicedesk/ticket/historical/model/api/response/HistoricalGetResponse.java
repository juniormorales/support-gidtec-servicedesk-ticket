package com.pe.gidtec.servicedesk.ticket.historical.model.api.response;

import com.pe.gidtec.servicedesk.ticket.audit.response.Audit;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.response.UserTicketResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name= "HistoricalGetResponse")
public class HistoricalGetResponse {

    @Schema(name = "historicalId",
            description = "Identificador del historico",
            example = "63c4e2158de4f459cae47eb9")
    private String historicalId;

    @Schema(name = "ticketId",
            description = "Identificador del ticket",
            example = "63c4e2158de4f459cae47ec1")
    private String ticketId;

    @Schema(name = "issue",
            description = "Asunto del ticket",
            example = "Agregar una IP a la lista blanca del servidor")
    private String issue;

    @Schema(name = "reason",
            description = "Motivo de reasignacion del ticket",
            example = "Se reasigno el ticket por una dificultad alta")
    private String reason;

    @Schema(name = "userReassigned",
            description = "Datos del usuario a quien se le reasigno el ticket")
    private UserTicketResponse userReassigned;

    @Schema(name = "userAssigned",
            description = "Datos del usuario que se le asignó el ticket")
    private UserTicketResponse userAssigned;

    @Schema(name = "audit",
            description = "Objeto de auditoria")
    private Audit audit;
}
