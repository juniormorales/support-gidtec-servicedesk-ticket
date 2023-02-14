package com.pe.gidtec.servicedesk.ticket.tickets.service.impl;

import com.pe.gidtec.servicedesk.lib.pdf.builder.PdfGenerator;
import com.pe.gidtec.servicedesk.lib.pdf.enums.TableHeaderSizeEnum;
import com.pe.gidtec.servicedesk.lib.pdf.model.TableEntityIterator;
import com.pe.gidtec.servicedesk.lib.pdf.model.TableHeaderEntity;
import com.pe.gidtec.servicedesk.ticket.common.enums.TicketStatusEnum;
import com.pe.gidtec.servicedesk.ticket.common.model.api.response.ResultResponse;
import com.pe.gidtec.servicedesk.ticket.common.model.header.CommonHeaders;
import com.pe.gidtec.servicedesk.ticket.common.util.ResponseStatus;
import com.pe.gidtec.servicedesk.ticket.config.TicketProperties;
import com.pe.gidtec.servicedesk.ticket.questionnaire.dao.QuestionnairesDao;
import com.pe.gidtec.servicedesk.ticket.common.enums.QuestionnaireStatusEnum;
import com.pe.gidtec.servicedesk.ticket.questionnaire.model.entity.QuestionnaireEntity;
import com.pe.gidtec.servicedesk.ticket.tickets.dao.TicketsDao;
import com.pe.gidtec.servicedesk.ticket.tickets.mapper.TicketsMapper;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.request.*;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.response.FilterLocalDateResponse;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.response.NumberTicketsClassifiedResponse;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.response.TicketDetailsResponse;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.response.TicketResponse;
import com.pe.gidtec.servicedesk.ticket.tickets.model.entity.TicketEntity;
import com.pe.gidtec.servicedesk.ticket.tickets.model.entity.UserTicket;
import com.pe.gidtec.servicedesk.ticket.tickets.service.TicketsService;
import com.pe.gidtec.servicedesk.ticket.user.dao.UsersDao;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketsServiceImpl implements TicketsService {

    private final TicketsDao ticketsDao;

    private final UsersDao usersDao;

    private final QuestionnairesDao questionnairesDao;

    private final TicketsMapper ticketsMapper;

    private final TicketProperties ticketProperties;


    @Override
    public Mono<ResultResponse<List<TicketResponse>>> getAllTicketsByFilter(FilterTicketsRequest request) {
        Flux<TicketEntity> response = ticketsDao.findAll();

        if (!Objects.isNull(request.getStatusCode())) {
            response = response.filter(filter -> request.getStatusCode().equals(filter.getStatusCode()));
        }

        if (!Objects.isNull(request.getTypeCode())) {
            response = response.filter(filter -> request.getTypeCode().equals(filter.getTypeCode()));
        }

        if (!Objects.isNull(request.getUserAssignedId())) {
            response = response.filter(filter -> {
                if (!Objects.isNull(filter.getUserAssigned())) {
                    return request.getUserAssignedId().equals(filter.getUserAssigned().getId());
                } else {
                    return false;
                }
            });
        }

        if (!Objects.isNull(request.getUserCreatorId())) {
            response = response.filter(filter -> request.getUserCreatorId().equals(filter.getUserCreator().getId()));
        }

        if(!Objects.isNull(request.getFilterLocalDate())) {
            LocalDateTime start = LocalDate.parse(request.getFilterLocalDate().getStartDate()).atStartOfDay();
            LocalDateTime end = LocalDate.parse(request.getFilterLocalDate().getEndDate()).atStartOfDay().plusDays(1);
            response = response.filter(filter -> start.isBefore(filter.getAudit().getCreatedDate()) && filter.getAudit().getCreatedDate().isBefore(end));
        }
        return response
                .map(ticketsMapper::entityToTicketResponse)
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
    public Mono<ResultResponse<TicketResponse>> createTicket(TicketPostRequest request, CommonHeaders headers) {
        return ticketsDao.saveTicket(ticketsMapper.ticketPostRequestToEntity(request, headers))
                .map(response -> ResultResponse.ok(ticketsMapper.entityToTicketResponse((response))))
                .onErrorReturn(ResultResponse.error(ResponseStatus.ERROR_SAVE_TICKET));
    }

    @Override
    public Mono<ResultResponse<TicketResponse>> deleteTicket(String ticketId, CommonHeaders headers) {
        return ticketsDao.verifyExistsTicketById(ticketId)
                .flatMap(bool -> {
                    if (Boolean.TRUE.equals(bool)) {
                        return ticketsDao.getTicketById(ticketId)
                                .map(entity -> buildRequestForDelete(entity, headers))
                                .flatMap(ticketsDao::saveTicket)
                                .map(response -> ResultResponse.ok(TicketResponse.builder().build()))
                                .onErrorReturn(ResultResponse.error(ResponseStatus.ERROR_SAVE_TICKET));
                    } else {
                        return Mono.just(ResultResponse.error(ResponseStatus.BAD_ID_TICKET_NOT_EXISTS));
                    }
                });
    }

    @Override
    public Mono<ResultResponse<TicketResponse>> updateStatusCodeTicket(StatusCodeTicketPutRequest request, CommonHeaders headers) {
        return ticketsDao.verifyExistsTicketById(request.getTicketId())
                .flatMap(bool -> {
                    if (Boolean.TRUE.equals(bool)) {
                        return ticketsDao.getTicketById(request.getTicketId())
                                .map(entity -> buildRequestForUpdate(entity, headers))
                                .map(entity -> {
                                    entity.setStatusCode(request.getCode());
                                    if (request.getCode().equals(TicketStatusEnum.CERRADO.getCode())) {
                                        questionnairesDao.saveQuestionnaire(this.createQuestionnaire(entity)).subscribe();
                                    }
                                    return entity;
                                })
                                .flatMap(ticketsDao::saveTicket)
                                .map(response -> ResultResponse.ok(ticketsMapper.entityToTicketResponse(response)))
                                .onErrorReturn(ResultResponse.error(ResponseStatus.ERROR_SAVE_TICKET));
                    } else {
                        return Mono.just(ResultResponse.error(ResponseStatus.BAD_ID_TICKET_NOT_EXISTS));
                    }
                });
    }

    @Override
    public Mono<ResultResponse<TicketResponse>> updateTypeCodeTicket(TypeCodeTicketPutRequest request, CommonHeaders headers) {
        return ticketsDao.verifyExistsTicketById(request.getTicketId())
                .flatMap(bool -> {
                    if (Boolean.TRUE.equals(bool)) {
                        return ticketsDao.getTicketById(request.getTicketId())
                                .map(entity -> buildRequestForUpdate(entity, headers))
                                .map(entity -> {
                                    entity.setTypeCode(request.getCode());
                                    return entity;
                                })
                                .flatMap(ticketsDao::saveTicket)
                                .map(response -> ResultResponse.ok(ticketsMapper.entityToTicketResponse(response)))
                                .onErrorReturn(ResultResponse.error(ResponseStatus.ERROR_SAVE_TICKET));
                    } else {
                        return Mono.just(ResultResponse.error(ResponseStatus.BAD_ID_TICKET_NOT_EXISTS));
                    }
                });
    }

    @Override
    public Mono<ResultResponse<TicketResponse>> setUserAssignedToTicket(UserAssignedPostRequest request, CommonHeaders headers) {
        return ticketsDao.verifyExistsTicketById(request.getTicketId())
                .flatMap(bool -> {
                    if (Boolean.TRUE.equals(bool)) {
                        return ticketsDao.getTicketById(request.getTicketId())
                                .map(entity -> buildRequestForUpdate(entity, headers))
                                .map(entity -> {
                                    entity.setUserAssigned(UserTicket.builder()
                                            .id(request.getUserAssigned().getId())
                                            .names(request.getUserAssigned().getNames())
                                            .lastNames(request.getUserAssigned().getLastNames())
                                            .companyName(request.getUserAssigned().getCompanyName())
                                            .build());
                                    entity.setStatusCode(TicketStatusEnum.EN_PROCESO.getCode());
                                    return entity;
                                })
                                .flatMap(ticketsDao::saveTicket)
                                .map(response -> ResultResponse.ok(ticketsMapper.entityToTicketResponse(response)))
                                .onErrorReturn(ResultResponse.error(ResponseStatus.ERROR_SAVE_TICKET));
                    } else {
                        return Mono.just(ResultResponse.error(ResponseStatus.BAD_ID_TICKET_NOT_EXISTS));
                    }
                });
    }

    @Override
    public Mono<ResultResponse<NumberTicketsClassifiedResponse>> getNumberOfTypesTicketOpened() {
        var numberTicketsClassified = NumberTicketsClassifiedResponse.builder().build();
        return ticketsDao.totalTicketsByStatusCode(TicketStatusEnum.ABIERTO.getCode())
                .flatMap(totalAB -> {
                    numberTicketsClassified.setNumberOfOpenedTickets(totalAB);
                    return ticketsDao.totalTicketsByTypeCode("CO");
                })
                .flatMap(totalCO -> {
                    numberTicketsClassified.setNumberOfQueryTickets(totalCO);
                    return ticketsDao.totalTicketsByTypeCode("RE");
                })
                .flatMap(totalRE -> {
                    numberTicketsClassified.setNumberOfRequestTickets(totalRE);
                    return ticketsDao.totalTicketsByTypeCode("IN");
                })
                .map(totalIN -> {
                    numberTicketsClassified.setNumberOfIncidenceTickets(totalIN);
                    return ResultResponse.ok(numberTicketsClassified);
                });
    }

    @Override
    public Mono<ResultResponse<List<FilterLocalDateResponse>>> getNumberTicketsByDateCreatedAndUserNullable(FilterLocalDateRequest request) {
        LocalDateTime start = LocalDate.parse(request.getStartDate()).atStartOfDay();
        LocalDateTime end = LocalDate.parse(request.getEndDate()).atStartOfDay().plusDays(1);

        Flux<TicketEntity> flux = null;
        if (!StringUtil.isNullOrEmpty(request.getUserCreatorId())) {
            flux = ticketsDao.findAllByStartDateAndEndDateAndUser(start, end, request.getUserCreatorId());
        } else {
            flux = ticketsDao.findAllByStartDateAndEndDate(start, end);
        }

        return flux
                .map(ticketEntity -> ticketEntity.getAudit().getCreatedDate().toLocalDate())
                .collectList()
                .map(dateCreated -> {
                    log.info("[getNumberTicketsByDateCreated] -> {}", dateCreated.toString());
                    List<FilterLocalDateResponse> result = new ArrayList<>();
                    getListWithUniqueValues(dateCreated).forEach(value -> result.add(FilterLocalDateResponse.builder()
                            .date(value.toString())
                            .countTickets(countValuesRepeatInList(dateCreated, value))
                            .build()));
                    return result;
                })
                .map(ResultResponse::ok);

    }

    @Override
    public Mono<ResultResponse<TicketDetailsResponse>> getTicketDetails(String ticketId) {
        return ticketsDao.verifyExistsTicketById(ticketId)
                .flatMap(bool -> {
                    if (Boolean.TRUE.equals(bool)) {
                        return ticketsDao.getTicketById(ticketId)
                                .flatMap(ticket ->
                                        usersDao.getUserById(ticket.getUserCreator().getId())
                                                .map(user -> ticketsMapper.entityToTicketDetailResponse(ticket, user))
                                                .map(ResultResponse::ok)
                                );
                    } else {
                        return Mono.just(ResultResponse.error(ResponseStatus.BAD_ID_TICKET_NOT_EXISTS));
                    }
                });
    }

    @Override
    public Mono<byte[]> generateReportTicketsByDateCreatedAndUserNullable(FilterLocalDateRequest request) {
        LocalDateTime start = LocalDate.parse(request.getStartDate()).atStartOfDay();
        LocalDateTime end = LocalDate.parse(request.getEndDate()).atStartOfDay().plusDays(1);

        Flux<TicketEntity> flux = null;
        if (!StringUtil.isNullOrEmpty(request.getUserCreatorId())) {
            flux = ticketsDao.findAllByStartDateAndEndDateAndUser(start, end, request.getUserCreatorId());
        } else {
            flux = ticketsDao.findAllByStartDateAndEndDate(start, end);
        }

        return flux
                .collectList()
                .map(list -> {
                    TableHeaderEntity[] headers = {
                            new TableHeaderEntity("ID", TableHeaderSizeEnum.MEDIUM),
                            new TableHeaderEntity("Creado por",TableHeaderSizeEnum.LARGE),
                            new TableHeaderEntity("Assigando a",TableHeaderSizeEnum.LARGE),
                            new TableHeaderEntity("Fecha creación",TableHeaderSizeEnum.LARGE),
                            new TableHeaderEntity("Tipo",TableHeaderSizeEnum.MEDIUM),
                            new TableHeaderEntity("Categoria",TableHeaderSizeEnum.LARGE),
                            new TableHeaderEntity("Estado",TableHeaderSizeEnum.MEDIUM),
                            new TableHeaderEntity("Asunto",TableHeaderSizeEnum.XLARGE),
                            new TableHeaderEntity("Duración",TableHeaderSizeEnum.SMALL)};
                    String[][] values = new String[list.size()][headers.length];
                    for (int i = 0; i < list.size(); i++) {
                        TicketEntity entity = list.get(i);
                        values[i] = new String[]{
                                entity.getTicketId(),
                                entity.getUserCreator().getNames() + " " + entity.getUserCreator().getLastNames(),
                                Objects.isNull(entity.getUserAssigned())?"": entity.getUserAssigned().getNames() + " " + entity.getUserAssigned().getLastNames(),
                                entity.getAudit().getCreatedDate().toString(),
                                ticketProperties.getTypeCode().get(entity.getTypeCode()),
                                ticketProperties.getCategoryCode().get(entity.getCategoryCode()),
                                ticketProperties.getStatusCode().get(entity.getStatusCode()),
                                entity.getIssue(),
                                entity.getDuration().toString() + " horas"
                        };
                    }
                    TableEntityIterator iterator = new TableEntityIterator(headers);
                    iterator.setData(values);

                    PdfGenerator generator;
                    if(list.isEmpty()){
                        generator = new PdfGenerator.Builder()
                                .addTitle("Reporte de Tickets desde la fecha " + request.getStartDate() + " hasta el " + request.getEndDate())
                                .addTitle("")
                                .addTitle("SIN TICKETS EN EL REPORTE")
                                .build();
                    } else {
                        generator = new PdfGenerator.Builder()
                                .addTitle("Reporte de Tickets desde la fecha " + request.getStartDate() + " hasta el " + request.getEndDate())
                                .addTable(iterator)
                                .build();
                    }
                    return generator.getBytes(true);
                });

    }

    private TicketEntity buildRequestForUpdate(TicketEntity entity, CommonHeaders headers) {
        entity.getAudit().setLastModifiedBy(headers.getUserCode());
        entity.getAudit().setLastModifiedDate(LocalDateTime.now());
        return entity;
    }

    private TicketEntity buildRequestForDelete(TicketEntity entity, CommonHeaders headers) {
        entity.getAudit().setLastModifiedBy(headers.getUserCode());
        entity.getAudit().setLastModifiedDate(LocalDateTime.now());
        entity.getAudit().setDeleted(Boolean.TRUE);
        return entity;
    }

    private <T> Long countValuesRepeatInList(List<T> list, T element) {
        return list.stream()
                .filter(data -> data.equals(element))
                .count();
    }

    private <T> List<T> getListWithUniqueValues(List<T> list) {
        HashSet<T> result = new HashSet<>(list);
        return new ArrayList<>(result)
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    private QuestionnaireEntity createQuestionnaire(TicketEntity ticketEntity) {
        return QuestionnaireEntity.builder()
                .userSurveyedId(ticketEntity.getUserCreator().getId())
                .ticketId(ticketEntity.getTicketId())
                .dateCreated(LocalDateTime.now())
                .statusCode(QuestionnaireStatusEnum.PENDIENTE.getCode())
                .build();
    }

}
