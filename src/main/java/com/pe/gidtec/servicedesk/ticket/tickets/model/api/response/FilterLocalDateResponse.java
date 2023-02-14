package com.pe.gidtec.servicedesk.ticket.tickets.model.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "FilterLocalDateResponse")
public class FilterLocalDateResponse {

    @Schema(name = "date",
            description = "Día de creación de un ticket",
            example = "2023-01-26")
    private String date;

    @Schema(name = "countTickets",
            description = "Cantidad de tickets creados en un dia respectivo",
            example = "142")
    private Long countTickets;
}
