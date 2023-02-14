package com.pe.gidtec.servicedesk.ticket.questionnaire.service.impl;

import com.pe.gidtec.servicedesk.ticket.common.model.api.response.ResultResponse;
import com.pe.gidtec.servicedesk.ticket.common.util.ResponseStatus;
import com.pe.gidtec.servicedesk.ticket.config.QuestionnaireProperties;
import com.pe.gidtec.servicedesk.ticket.questionnaire.dao.QuestionnairesDao;
import com.pe.gidtec.servicedesk.ticket.common.enums.QuestionnaireStatusEnum;
import com.pe.gidtec.servicedesk.ticket.questionnaire.model.api.request.QuestionnairePostRequest;
import com.pe.gidtec.servicedesk.ticket.questionnaire.model.api.response.Question;
import com.pe.gidtec.servicedesk.ticket.questionnaire.model.api.response.QuestionnaireGetResponse;
import com.pe.gidtec.servicedesk.ticket.questionnaire.model.entity.QuestionnaireEntity;
import com.pe.gidtec.servicedesk.ticket.questionnaire.service.QuestionnairesService;
import com.pe.gidtec.servicedesk.ticket.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionnairesServiceImpl implements QuestionnairesService {

    private final QuestionnairesDao questionnairesDao;

    private final QuestionnaireProperties questionnaireProperties;

    @Override
    public Mono<ResultResponse<QuestionnaireGetResponse>> findQuestionnaireByTicket(String ticketId) {
        return questionnairesDao.findByTicket(ticketId)
                .map(entity -> {
                    if (Objects.isNull(entity.getQuestionnaireId())) {
                        return ResultResponse.noData();
                    } else {
                        return ResultResponse.ok(this.entityToGet(entity));
                    }
                });
    }

    @Override
    public Mono<ResultResponse<List<QuestionnaireGetResponse>>> findAll() {
        return questionnairesDao.findAll()
                .map(this::entityToGet)
                .collectList()
                .map(list -> {
                    if (list.isEmpty()) {
                        return ResultResponse.ok(new ArrayList<>());
                    } else {
                        return ResultResponse.ok(list);
                    }
                });
    }

    @Override
    public Mono<ResultResponse<QuestionnaireGetResponse>> fillQuestionnaire(QuestionnairePostRequest request) {
        return questionnairesDao.verifyExistsQuestionnaireById(request.getQuestionnaireId())
                .flatMap(bool -> {
                    if (Boolean.FALSE.equals(bool)) {
                        return Mono.just(ResultResponse.error(ResponseStatus.BAD_ID_QUESTIONNAIRE_NOT_EXISTS));
                    } else {
                        return questionnairesDao.findByQuestionnaireId(request.getQuestionnaireId())
                                .flatMap(questionnaireEntity -> questionnairesDao.saveQuestionnaire(buildToEntity(request, questionnaireEntity))
                                        .map(this::entityToGet)
                                        .map(ResultResponse::ok)
                                        .onErrorReturn(ResultResponse.error(ResponseStatus.ERROR_SAVE_QUESTIONNAIRE)));
                    }
                });
    }

    @Override
    public Mono<ResultResponse<List<QuestionnaireGetResponse>>> findAllQuestionnairePendingByUser(String userId) {
        return questionnairesDao.findAllByStatusCodeAndUser(QuestionnaireStatusEnum.PENDIENTE.getCode(), userId)
                .map(this::entityToGet)
                .collectList()
                .map((list -> {
                    if (list.isEmpty()) {
                        return ResultResponse.noData();
                    } else {
                        return ResultResponse.ok(list);
                    }
                }));
    }

    private QuestionnaireEntity buildToEntity(QuestionnairePostRequest request, QuestionnaireEntity entity) {
        entity.setDateCompleted(LocalDateTime.now());
        entity.setProblemOnTime(Boolean.parseBoolean(request.getProblemOnTime()));
        entity.setProblemSolved(Boolean.parseBoolean(request.getProblemSolved()));
        entity.setRating(Integer.parseInt(request.getRating()));
        entity.setStatusCode(QuestionnaireStatusEnum.COMPLETADO.getCode());
        return entity;
    }

    private QuestionnaireGetResponse entityToGet(QuestionnaireEntity entity) {
        return QuestionnaireGetResponse.builder()
                .questionnaireId(entity.getQuestionnaireId())
                .dateCreated(entity.getDateCreated().toString())
                .problemOnTime(Question.builder()
                        .question(Constants.QUESTION_ONE)
                        .response(Constants.getResponseBoolean(entity.getProblemOnTime()))
                        .build())
                .problemSolved(Question.builder()
                        .question(Constants.QUESTION_TWO)
                        .response(Constants.getResponseBoolean(entity.getProblemSolved()))
                        .build())
                .rating(Question.builder()
                        .question(Constants.QUESTION_THREE)
                        .response(entity.getRating().toString())
                        .build())
                .ticketId(entity.getTicketId())
                .dateCompleted(Objects.nonNull(entity.getDateCompleted())? entity.getDateCompleted().toString() : null)
                .status(questionnaireProperties.getStatusCode().get(entity.getStatusCode()))
                .build();
    }
}
