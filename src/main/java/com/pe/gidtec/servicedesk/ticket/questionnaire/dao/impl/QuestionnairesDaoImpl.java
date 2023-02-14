package com.pe.gidtec.servicedesk.ticket.questionnaire.dao.impl;

import com.pe.gidtec.servicedesk.ticket.questionnaire.dao.QuestionnairesDao;
import com.pe.gidtec.servicedesk.ticket.questionnaire.model.entity.QuestionnaireEntity;
import com.pe.gidtec.servicedesk.ticket.questionnaire.repository.QuestionnairesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class QuestionnairesDaoImpl implements QuestionnairesDao {

    private final QuestionnairesRepository questionnairesRepository;

    @Override
    public Flux<QuestionnaireEntity> findAll() {
        return questionnairesRepository.findAllByOrderByDateCreatedAsc();
    }

    @Override
    public Mono<QuestionnaireEntity> findByTicket(String ticketId) {
        return questionnairesRepository.findFirstByTicketId(ticketId)
                .switchIfEmpty(Mono.just(QuestionnaireEntity.builder().build()));
    }

    @Override
    public Mono<QuestionnaireEntity> saveQuestionnaire(QuestionnaireEntity entity) {
        return questionnairesRepository.save(entity)
                .doOnSuccess(message -> log.info("Resultados de cuestionario " + message.toString() + " guardado con éxito."))
                .onErrorResume( ex -> Mono.error(new Exception("Ocurrio un error al intentar guardar las respuestas del cuestionario")));
    }

    @Override
    public Mono<Boolean> verifyExistsQuestionnaireById(String questionnaireId) {
        return questionnairesRepository.existsById(questionnaireId);
    }

    @Override
    public Mono<QuestionnaireEntity> findByQuestionnaireId(String questionnaireId) {
        return questionnairesRepository.findById(questionnaireId);
    }

    @Override
    public Flux<QuestionnaireEntity> findAllByStatusCodeAndUser(String statusCode, String userId) {
        return questionnairesRepository.findAllByStatusCodeAndUserSurveyedId(statusCode,userId);
    }
}
