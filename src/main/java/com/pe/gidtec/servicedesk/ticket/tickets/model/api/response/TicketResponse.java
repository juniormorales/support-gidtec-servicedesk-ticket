package com.pe.gidtec.servicedesk.ticket.tickets.model.api.response;

import com.pe.gidtec.servicedesk.ticket.audit.response.Audit;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name= "TicketResponse")
public class TicketResponse {

    @Schema(name = "ticketId",
            description = "Identificador del ticket",
            example = "1ab24sof5e1vxc")
    private String ticketId;

    @Schema(name = "issue",
            description = "Asunto del ticket",
            example = "Agregar una IP a la lista blanca del servidor")
    private String issue;

    @Schema(name = "userCreator",
            description = "Datos del usuario que generó el ticket")
    private UserTicketResponse userCreator;

    @Schema(name = "userAssigned",
            description = "Datos del usuario que se le asignó el ticket")
    private UserTicketResponse userAssigned;

    @Schema(name = "typeTicket",
            description = "Datos del tipo de ticket")
    private TypeTicketResponse typeTicket;

    @Schema(name = "statusTicket",
            description = "Datos del estado del ticket")
    private StatusTicketResponse statusTicket;

    @Schema(name = "categoryTicket",
            description = "Datos de la categoria del ticket")
    private CategoryTicketResponse categoryTicket;

    @Schema(name = "audit",
            description = "Objeto de auditoria")
    private Audit audit;
}
