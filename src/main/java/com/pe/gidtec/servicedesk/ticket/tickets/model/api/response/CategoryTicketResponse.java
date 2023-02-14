package com.pe.gidtec.servicedesk.ticket.tickets.model.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "CategoryTicketResponse")
public class CategoryTicketResponse {

    @Schema(name = "code",
            description = "Código de la categoria del ticket",
            example = "SE")
    private String code;

    @Schema(name = "name",
            description = "Nombre de la categoria del ticket",
            example = "Servidores")
    private String name;
}
