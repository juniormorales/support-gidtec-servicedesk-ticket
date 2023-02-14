package com.pe.gidtec.servicedesk.ticket.controller;

import com.pe.gidtec.servicedesk.lib.annotation.HttpHeadersMapping;
import com.pe.gidtec.servicedesk.ticket.common.model.api.response.ResultResponse;
import com.pe.gidtec.servicedesk.ticket.common.model.header.CommonHeaders;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.request.*;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.response.FilterLocalDateResponse;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.response.NumberTicketsClassifiedResponse;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.response.TicketDetailsResponse;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.response.TicketResponse;
import com.pe.gidtec.servicedesk.ticket.tickets.service.TicketsService;
import com.pe.gidtec.servicedesk.ticket.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/support/api/tickets")
@Tag(name = "Tickets", description = "Gestiona la información de los tickets")
@Validated
public class TicketsController {

    private final TicketsService ticketsService;

    private final ResponseUtil responseUtil;

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    },
            summary = "Permite listar los tickets segun filtros",
            method = "POST")
    @PostMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<List<TicketResponse>>>> findAllByFilter(
            @Valid
            @RequestBody FilterTicketsRequest request
    ) {
        return ticketsService.getAllTicketsByFilter(request)
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    },
            summary = "Permite registrar la información de un ticket",
            method = "POST")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<TicketResponse>>> create(
            @ParameterObject
            @HttpHeadersMapping
                    CommonHeaders headers,
            @Valid
            @RequestBody
                    TicketPostRequest request
    ) {
        return ticketsService.createTicket(request, headers)
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    },
            summary = "Permite actualizar el estado de un ticket",
            method = "PUT")
    @PutMapping(value = "/status-code", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<TicketResponse>>> updateStatusCode(
            @ParameterObject
            @HttpHeadersMapping
                    CommonHeaders headers,
            @Valid
            @RequestBody
                    StatusCodeTicketPutRequest request
    ) {
        return ticketsService.updateStatusCodeTicket(request, headers)
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    },
            summary = "Permite actualizar el tipo de un ticket",
            method = "PUT")
    @PutMapping(value = "/type-code", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<TicketResponse>>> updateTypeCode(
            @ParameterObject
            @HttpHeadersMapping
                    CommonHeaders headers,
            @Valid
            @RequestBody
                    TypeCodeTicketPutRequest request
    ) {
        return ticketsService.updateTypeCodeTicket(request, headers)
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    },
            summary = "Permite asignar un usuario para la atención de un ticket",
            method = "POST")
    @PostMapping(value = "/assign-user", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<TicketResponse>>> assignUserToTicket(
            @ParameterObject
            @HttpHeadersMapping
                    CommonHeaders headers,
            @Valid
            @RequestBody
                    UserAssignedPostRequest request
    ) {
        return ticketsService.setUserAssignedToTicket(request, headers)
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = TicketDetailsResponse.class)))
    },
            summary = "Permite obtener los detalles de un ticket mediante su identificador 'ticketId'",
            method = "GET")
    @GetMapping(value = "/details/{ticketId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<TicketDetailsResponse>>> getTicketDetails(
            @Parameter(name = "ticketId", example = "s58xe8d0x6", description = "Identificador del ticket")
            @PathVariable("ticketId")
            @NotBlank(message = "El valor de 'ticketId' no debe ser vacio")
                    String ticketId
    ) {
        return ticketsService.getTicketDetails(ticketId)
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    },
            summary = "Permite eliminar un ticket del registro",
            method = "DELETE")
    @DeleteMapping(value = "/{ticketId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<TicketResponse>>> delete(
            @ParameterObject
            @HttpHeadersMapping
                    CommonHeaders headers,
            @Parameter(name = "ticketId", example = "s58xe8d0x6", description = "Identificador del ticket")
            @PathVariable("ticketId")
            @NotBlank
                    String ticketId
    ) {
        return ticketsService.deleteTicket(ticketId, headers)
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(
            summary = "Permite generar un reporte de tickets en pdf segun un rango de fechas, si se le envia el id del usuario, lista los tickets por usuario",
            method = "POST")
    @PostMapping(value = "/tickets/generate-pdf", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_PDF_VALUE})
    public Mono<DataBuffer> generatePDFByFilterDateAndUser(
            @Valid
            @RequestBody FilterLocalDateRequest request
    ) {
        return ticketsService.generateReportTicketsByDateCreatedAndUserNullable(request)
                .map(response -> {
                    DataBufferFactory bufferFactory = new DefaultDataBufferFactory();
                    DataBuffer buffer = bufferFactory.allocateBuffer(response.length);
                    buffer.write(response);
                    return buffer;
                });
    }


}
