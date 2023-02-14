package com.pe.gidtec.servicedesk.ticket.tickets.model.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "NumberTicketsClassifiedResponse")
public class NumberTicketsClassifiedResponse {

    @Schema(name = "numberOfOpenedTickets",
            description = "Cantidad de tickets con el estado abierto",
            example = "100")
    private Long numberOfOpenedTickets;

    @Schema(name = "numberOfIncidenceTickets",
            description = "Cantidad de tickets del tipo incidencia",
            example = "20")
    private Long numberOfIncidenceTickets;

    @Schema(name = "numberOfRequestTickets",
            description = "Cantidad de tickets del tipo requerimiento",
            example = "48")
    private Long numberOfRequestTickets;

    @Schema(name = "numberOfQueryTickets",
            description = "Cantidad de tickets del tipo consulta",
            example = "5")
    private Long numberOfQueryTickets;
}
