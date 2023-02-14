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
@Schema(name = "TypeTicket")
public class TypeTicket {

    @NotBlank(message = "El valor de 'code' en TypeTicket no debe ser vacio.")
    @Pattern(regexp = "^(CO|RE|IN)$", message = "El valor de 'code' admite solo los siguientes valores: CO, RE y IN.")
    @Schema(name = "code",
            description = "Codigo del tipo del ticket",
            example = "RE : Requerimiento" ,pattern = "^(CO|RE|IN)$")
    private String code;
}
