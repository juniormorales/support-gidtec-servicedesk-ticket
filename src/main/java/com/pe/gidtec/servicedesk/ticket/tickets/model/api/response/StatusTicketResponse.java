package com.pe.gidtec.servicedesk.ticket.tickets.model.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "StatusTicketResponse")
public class StatusTicketResponse {

    @Schema(name = "code",
            description = "Código del estado del ticket",
            example = "RE")
    private String code;

    @Schema(name = "name",
            description = "Nombre del estado del ticket",
            example = "Resuelto")
    private String name;
}
