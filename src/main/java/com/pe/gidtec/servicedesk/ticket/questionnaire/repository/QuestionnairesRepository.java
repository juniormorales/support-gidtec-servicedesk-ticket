package com.pe.gidtec.servicedesk.ticket.questionnaire.repository;

import com.pe.gidtec.servicedesk.ticket.questionnaire.model.entity.QuestionnaireEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QuestionnairesRepository extends ReactiveMongoRepository<QuestionnaireEntity, String> {

    Flux<QuestionnaireEntity> findAllByOrderByDateCreatedAsc();

    Mono<QuestionnaireEntity> findFirstByTicketId(String ticketId);

    Flux<QuestionnaireEntity> findAllByStatusCodeAndUserSurveyedId(String statusCode, String userId);
}
