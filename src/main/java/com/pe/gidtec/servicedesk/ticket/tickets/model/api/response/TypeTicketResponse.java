package com.pe.gidtec.servicedesk.ticket.tickets.model.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "TypeTicketResponse")
public class TypeTicketResponse {

    @Schema(name = "code",
            description = "Codigo del tipo del ticket",
            example = "CO")
    private String code;

    @Schema(name = "name",
            description = "Nombre del tipo del ticket",
            example = "Requerimiento")
    private String name;

}
