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
public class TicketDetailsResponse {

    @Schema(name = "ticketId",
            description = "Identificador del ticket",
            example = "1ab24sof5e1vxc")
    private String ticketId;

    @Schema(name = "issue",
            description = "Asunto del ticket",
            example = "Agregar una IP a la lista blanca del servidor")
    private String issue;

    @Schema(name = "instance",
            description = "Detalle del asunto del ticket",
            example = "Se necesita agregar a la IP 172.161.16.6 a la lista blanca del servidor host SRV-0000")
    private String instance;

    @Schema(name = "duration",
            description = "Duración del ticket en horas",
            example = "12")
    private Integer duration;

    @Schema(name = "dueDate",
            description = "Fecha de vencimiento del ticket",
            example = "2022-08-15T20:00:00")
    private String dueDate;

    @Schema(name = "userCreator",
            description = "Datos del usuario que generó el ticket")
    private UserTicketResponse userCreator;

    @Schema(name = "userAssigned",
            description = "Datos del usuario que se le asignó el ticket")
    private UserTicketResponse userAssigned;

    @Schema(name = "webSite",
            description = "URL de la pagina web de la empresa del usuario cliente",
            example = "www.company.com.pe")
    private String webSite;

    @Schema(name = "email",
            description = "Correo electronico del usuario cliente",
            example = "user1@gidtec.com.pe")
    private String email;

    @Schema(name = "phoneNumber",
            description = "Número de telefono del usuario cliente",
            example = "987654321")
    private String phoneNumber;

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
