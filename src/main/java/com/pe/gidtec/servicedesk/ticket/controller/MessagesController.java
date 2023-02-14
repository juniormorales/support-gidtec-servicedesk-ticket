package com.pe.gidtec.servicedesk.ticket.controller;

import com.pe.gidtec.servicedesk.lib.annotation.HttpHeadersMapping;
import com.pe.gidtec.servicedesk.ticket.common.model.api.response.ResultResponse;
import com.pe.gidtec.servicedesk.ticket.common.model.header.CommonHeaders;
import com.pe.gidtec.servicedesk.ticket.messages.model.api.request.MessagePostRequest;
import com.pe.gidtec.servicedesk.ticket.messages.model.api.request.MessagePutRequest;
import com.pe.gidtec.servicedesk.ticket.messages.model.api.response.MessageGetResponse;
import com.pe.gidtec.servicedesk.ticket.messages.model.api.response.TotalMessageResponse;
import com.pe.gidtec.servicedesk.ticket.messages.service.MessagesResponseService;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.request.TicketPostRequest;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.response.NumberTicketsClassifiedResponse;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.response.TicketResponse;
import com.pe.gidtec.servicedesk.ticket.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
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
@RequestMapping("/support/api/messages")
@Tag(name = "Messages", description = "Gestiona los mensajes enviados y respondidos de los tickets")
@Validated
public class MessagesController {

    private final MessagesResponseService messagesResponseService;

    private final ResponseUtil responseUtil;

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = TotalMessageResponse.class)))
    },
            summary = "Permite obtener los mensajes enviados y respondidos en un ticket con estructura de arbol (mensajes que contienen dentro de ellas las respuestas)",
            method = "GET")
    @GetMapping(value = "/search-tree/{ticketId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<TotalMessageResponse>>> getTotalMessagesInTreeByTicket(
            @Parameter(name = "ticketId", example = "s58xe8d0x6", description = "Identificador del ticket")
            @PathVariable("ticketId")
            @NotBlank(message = "El valor de 'ticketId' no debe ser vacio")
                    String ticketId
    ) {
        return messagesResponseService.findMessagesInTreeByTicket(ticketId)
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = TotalMessageResponse.class)))
    },
            summary = "Permite obtener en lista los mensajes enviados y respondidos en un ticket por orden de fecha",
            method = "GET")
    @GetMapping(value = "/search/{ticketId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<List<TotalMessageResponse>>>> getTotalMessagesInListByTicket(
            @Parameter(name = "ticketId", example = "s58xe8d0x6", description = "Identificador del ticket")
            @PathVariable("ticketId")
            @NotBlank(message = "El valor de 'ticketId' no debe ser vacio")
                    String ticketId
    ) {
        return messagesResponseService.findMessagesInListByTicket(ticketId)
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    },
            summary = "Permite registrar un mensaje de respuesta hecho en un ticket",
            method = "POST")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<MessageGetResponse>>> create(
            @ParameterObject
            @HttpHeadersMapping
                    CommonHeaders headers,
            @Valid
            @RequestBody
                    MessagePostRequest request
    ) {
        return messagesResponseService.createMessageResponse(request, headers)
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    },
            summary = "Permite actualizar un mensaje de respuesta hecho en un ticket",
            method = "PUT")
    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<MessageGetResponse>>> update(
            @ParameterObject
            @HttpHeadersMapping
                    CommonHeaders headers,
            @Valid
            @RequestBody
                    MessagePutRequest request
    ) {
        return messagesResponseService.updateMessageResponse(request, headers)
                .map(responseUtil::getResponseEntityStatus);
    }
}
