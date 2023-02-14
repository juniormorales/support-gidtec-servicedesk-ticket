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
@Schema(name = "CategoryTicket")
public class CategoryTicket {

    @NotBlank(message = "El valor de 'code' en CategoryTicket no debe ser vacio.")
    @Pattern(regexp = "^(DI|PC|SE|FW|IN|AS|BL|OT)$", message = "El valor de 'code' admite solo los siguientes valores: DI, PC, SE, FW, IN, AS, BL y OT.")
    @Schema(name = "code",
            description = "Código de la categoria del ticket",
            example = "SE : Servidores", pattern = "^(DI|PC|SE|FW|IN|AS|BL|OT)$")
    private String code;

}
