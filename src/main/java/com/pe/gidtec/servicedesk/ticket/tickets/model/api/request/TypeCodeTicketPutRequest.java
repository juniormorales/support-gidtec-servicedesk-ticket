package com.pe.gidtec.servicedesk.ticket.tickets.model.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "TicketByCodePutRequest")
public class TypeCodeTicketPutRequest {

    @NotBlank(message = "El valor de 'ticketId' no puede ser vacio.")
    @Schema(name = "ticketId",
            description = "Identificador del ticket a modificar",
            example = "1ab24sof5e1vxc")
    private String ticketId;

    @NotBlank(message = "El valor de 'code' no puede ser vacio.")
    @Pattern(regexp = "^(CO|RE|IN)$", message = "El valor de 'code' admite solo los siguientes valores: CO, RE y IN.")
    @Schema(name = "code",
            description = "Codigo del tipo del ticket a modificar" ,
            example = "RE" ,pattern = "^(CO|RE|IN)$")
    private String code;
}
