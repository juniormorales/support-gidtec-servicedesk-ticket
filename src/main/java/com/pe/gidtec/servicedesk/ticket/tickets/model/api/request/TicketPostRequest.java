package com.pe.gidtec.servicedesk.ticket.tickets.model.api.request;

import com.pe.gidtec.servicedesk.ticket.tickets.model.CategoryTicket;
import com.pe.gidtec.servicedesk.ticket.tickets.model.TypeTicket;
import com.pe.gidtec.servicedesk.ticket.user.model.request.UserRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "TicketPostRequest")
public class TicketPostRequest {

    @NotNull(message = "El valor de 'userCreator' no puede ser nulo.")
    @Schema(name = "userCreator",
            description = "Datos del usuario que genera el ticket")
    private UserRequest userCreator;

    @NotNull(message = "El valor de 'type' no puede ser nulo.")
    @Schema(name = "type",
            description = "Datos del tipo del ticket")
    private TypeTicket type;

    @NotNull(message = "El valor de 'category' no puede ser nulo.")
    @Schema(name = "category",
            description = "Datos de la categoria del ticket")
    private CategoryTicket category;

    @NotBlank(message = "El valor de 'issue' no puede ser vacio.")
    @Schema(name = "issue",
            description = "El asunto o título del ticket",
            example = "AGREGAR UNA IP A LA LISTA BLANCA DEL SERVIDOR")
    private String issue;

    @NotBlank(message = "El valor de 'instance' no puede ser vacio.")
    @Schema(name = "instance",
            description = "Describe la consulta, el requerimiento o la incidencia del ticket",
            example = "Se necesita agregar a la IP 172.161.16.6 a la lista blanca del servidor host SRV-0000")
    private String instance;

}
