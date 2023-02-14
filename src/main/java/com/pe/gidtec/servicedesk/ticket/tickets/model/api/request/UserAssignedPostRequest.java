package com.pe.gidtec.servicedesk.ticket.tickets.model.api.request;

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
@Schema(name = "UserAssignedPostRequest")
public class UserAssignedPostRequest {

    @NotBlank(message = "El valor de 'ticketId' no puede ser vacio.")
    @Schema(name = "ticketId",
            description = "Identificador del ticket a modificar",
            example = "1ab24sof5e1vxc")
    private String ticketId;

    @NotNull(message = "El valor de 'userAssigned' no puede ser nulo.")
    @Schema(name = "userAssigned",
            description = "Datos del usuario al que se le asignará el ticket")
    private UserRequest userAssigned;
}
