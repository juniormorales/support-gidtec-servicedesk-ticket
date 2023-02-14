package com.pe.gidtec.servicedesk.ticket.questionnaire.service;

import com.pe.gidtec.servicedesk.ticket.common.model.api.response.ResultResponse;
import com.pe.gidtec.servicedesk.ticket.questionnaire.model.api.request.QuestionnairePostRequest;
import com.pe.gidtec.servicedesk.ticket.questionnaire.model.api.response.QuestionnaireGetResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface QuestionnairesService {

    Mono<ResultResponse<QuestionnaireGetResponse>> findQuestionnaireByTicket(String ticketId);

    Mono<ResultResponse<List<QuestionnaireGetResponse>>> findAll();

    Mono<ResultResponse<QuestionnaireGetResponse>> fillQuestionnaire(QuestionnairePostRequest request);

    Mono<ResultResponse<List<QuestionnaireGetResponse>>> findAllQuestionnairePendingByUser(String userId);
}
