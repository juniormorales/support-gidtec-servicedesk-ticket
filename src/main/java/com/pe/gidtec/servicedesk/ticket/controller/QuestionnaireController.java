package com.pe.gidtec.servicedesk.ticket.controller;


import com.pe.gidtec.servicedesk.ticket.common.model.api.response.ResultResponse;
import com.pe.gidtec.servicedesk.ticket.questionnaire.model.api.request.QuestionnairePostRequest;
import com.pe.gidtec.servicedesk.ticket.questionnaire.model.api.response.QuestionnaireGetResponse;
import com.pe.gidtec.servicedesk.ticket.questionnaire.service.QuestionnairesService;
import com.pe.gidtec.servicedesk.ticket.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/support/api/questionnaires")
@Tag(name = "Questionnaires", description = "Gestiona las encuestas hechas a los usuarios al resolver un ticket")
@Validated
public class QuestionnaireController {

    private final QuestionnairesService questionnairesService;

    private final ResponseUtil responseUtil;

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    },
            summary = "Permite registrar el llenado de un cuestionario con respuestas al completar un ticket",
            method = "POST")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<QuestionnaireGetResponse>>> create(
            @Valid
            @RequestBody
                    QuestionnairePostRequest request
    ) {
        return questionnairesService.fillQuestionnaire(request)
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = QuestionnaireGetResponse.class)))
    },
            summary = "Permite obtener la lista de todas las encuestas hechas",
            method = "GET")
    @GetMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<List<QuestionnaireGetResponse>>>> findAll() {
        return questionnairesService.findAll()
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = QuestionnaireGetResponse.class)))
    },
            summary = "Permite obtener la encuesta realizada de un ticket mediante su identificador 'ticketId'",
            method = "GET")
    @GetMapping(value = "/{ticketId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<QuestionnaireGetResponse>>> getQuestionnaireByTicket(
            @Parameter(name = "ticketId", example = "s58xe8d0x6", description = "Identificador del ticket")
            @PathVariable("ticketId")
            @NotBlank(message = "El valor de 'ticketId' no debe ser vacio")
                    String ticketId
    ) {
        return questionnairesService.findQuestionnaireByTicket(ticketId)
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = QuestionnaireGetResponse.class)))
    },
            summary = "Permite obtener las encuesta pendientes de un usuario mediante su identificador 'userId'",
            method = "GET")
    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<List<QuestionnaireGetResponse>>>> getQuestionnaireByUser(
            @Parameter(name = "userId", example = "s58xe8d0x6", description = "Identificador del usuario")
            @PathVariable("userId")
            @NotBlank(message = "El valor de 'userId' no debe ser vacio")
                    String userId
    ) {
        return questionnairesService.findAllQuestionnairePendingByUser(userId)
                .map(responseUtil::getResponseEntityStatus);
    }
}
