package com.pe.gidtec.servicedesk.ticket.tickets.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "StatusTicket")
public class StatusTicket {

    @NotBlank(message = "El valor de 'code' en StatusTicket no debe ser vacio.")
    @Pattern(regexp = "^(RE|CE|AB)$", message = "El valor de 'code' admite solo los siguientes valores: RE, CE y AB.")
    @Schema(name = "code",
            description = "Codigo del estado del ticket",
            example = "RE")
    private String code;
}
