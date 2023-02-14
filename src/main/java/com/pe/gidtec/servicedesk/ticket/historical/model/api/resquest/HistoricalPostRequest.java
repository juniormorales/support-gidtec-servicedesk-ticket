package com.pe.gidtec.servicedesk.ticket.historical.model.api.resquest;

import com.pe.gidtec.servicedesk.ticket.tickets.model.api.response.UserTicketResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "HistoricalPostRequest")
public class HistoricalPostRequest {

    @Schema(name = "ticketId",
            description = "Identificador del ticket el cual se le reasignara el responsable",
            example = "63c4e2158de4f459cae47ec1")
    @NotBlank(message = "El valor de 'ticketId' no puede estar vacio")
    private String ticketId;

    @Schema(name = "reason",
            description = "Motivo de reasignacion del ticket",
            example = "Se reasigno el ticket por una dificultad alta")
    @NotBlank(message = "Debe indicar obligatoriamente un motivo para la reasignacion de ticket")
    private String reason;

    @Schema(name = "userReassignedId",
            description = "Identificador del usuario a quien se le reasigno el ticket",
            example = "63c48d5ab44bc01337a70bf1")
    @NotBlank(message = "El valor de 'userReassignedId' no puede estar vacio")
    private String userReassignedId;

    @Schema(name = "userAssignedId",
            description = "Identificador del usuario que se le asigno el ticket",
            example = "63c48d5ab44bc01337a70be1")
    @NotBlank(message = "El valor de 'userAssignedId' no puede estar vacio")
    private String userAssignedId;
}
