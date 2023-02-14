package com.pe.gidtec.servicedesk.ticket.controller;

import com.pe.gidtec.servicedesk.ticket.common.model.api.response.ResultResponse;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.request.FilterLocalDateRequest;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.response.FilterLocalDateResponse;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.response.NumberTicketsClassifiedResponse;
import com.pe.gidtec.servicedesk.ticket.tickets.service.TicketsService;
import com.pe.gidtec.servicedesk.ticket.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/support/api/dashboard")
@Tag(name = "Dashboard", description = "Gestiona la información de diferentes tipos de graficos a mostrar")
@Validated
public class DashboardController {

    private final TicketsService ticketsService;

    private final ResponseUtil responseUtil;

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = NumberTicketsClassifiedResponse.class)))
    },
            summary = "Permite obtener la cantidad de tickets por estado abierto, tipo incidencia, requerimiento y consulta",
            method = "GET")
    @GetMapping(value = "/tickets/total-by-types", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<NumberTicketsClassifiedResponse>>> getTotalTicketsByStatusAndType() {
        return ticketsService.getNumberOfTypesTicketOpened()
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = FilterLocalDateResponse.class)))
    },
            summary = "Permite obtener la cantidad de tickets creados por dia segun un rango de fechas, si se le envia el id del usuario, lista los tickets por usuario",
            method = "POST")
    @PostMapping(value = "/tickets/total-by-range-date-user", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<List<FilterLocalDateResponse>>>> getNumberTicketsByFilterDateAndUser(
            @Valid
            @RequestBody FilterLocalDateRequest request
            ) {
        return ticketsService.getNumberTicketsByDateCreatedAndUserNullable(request)
                .map(responseUtil::getResponseEntityStatus);
    }
}
