package com.pe.gidtec.servicedesk.ticket.questionnaire.dao;

import com.pe.gidtec.servicedesk.ticket.questionnaire.model.entity.QuestionnaireEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QuestionnairesDao {

    Flux<QuestionnaireEntity> findAll();

    Mono<QuestionnaireEntity> findByTicket(String ticketId);

    Mono<QuestionnaireEntity> saveQuestionnaire(QuestionnaireEntity entity);

    Mono<Boolean> verifyExistsQuestionnaireById(String questionnaireId);

    Mono<QuestionnaireEntity> findByQuestionnaireId(String questionnaireId);

    Flux<QuestionnaireEntity> findAllByStatusCodeAndUser(String statusCode, String userId);
}
