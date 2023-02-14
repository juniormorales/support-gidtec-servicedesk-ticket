package com.pe.gidtec.servicedesk.ticket.controller;

import com.pe.gidtec.servicedesk.lib.annotation.HttpHeadersMapping;
import com.pe.gidtec.servicedesk.ticket.common.model.api.response.ResultResponse;
import com.pe.gidtec.servicedesk.ticket.common.model.header.CommonHeaders;
import com.pe.gidtec.servicedesk.ticket.historical.model.api.response.HistoricalGetResponse;
import com.pe.gidtec.servicedesk.ticket.historical.model.api.resquest.HistoricalPostRequest;
import com.pe.gidtec.servicedesk.ticket.historical.service.HistoricalsService;
import com.pe.gidtec.servicedesk.ticket.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
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
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/support/api/historicals")
@Tag(name = "Historicals", description = "Gestiona historicos de casos donde se reasigna tickets")
@Validated
public class HistoricalController {

    private final HistoricalsService historicalsService;

    private final ResponseUtil responseUtil;

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    },
            summary = "Permite registrar un historico de caso cuando se reasigna un ticket",
            method = "POST")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<HistoricalGetResponse>>> create(
            @ParameterObject
            @HttpHeadersMapping
                    CommonHeaders headers,
            @Valid
            @RequestBody
                    HistoricalPostRequest request
    ) {
        return historicalsService.createHistorical(request, headers)
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = HistoricalGetResponse.class)))
    },
            summary = "Permite obtener la lista de todos los historicos de casos generados por tickets",
            method = "GET")
    @GetMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<List<HistoricalGetResponse>>>> findAll() {
        return historicalsService.getAllHistoricals()
                .map(responseUtil::getResponseEntityStatus);
    }
}
