package com.pe.gidtec.servicedesk.ticket.historical.service.impl;

import com.pe.gidtec.servicedesk.ticket.common.model.api.response.ResultResponse;
import com.pe.gidtec.servicedesk.ticket.common.model.header.CommonHeaders;
import com.pe.gidtec.servicedesk.ticket.common.util.ResponseStatus;
import com.pe.gidtec.servicedesk.ticket.historical.dao.HistoricalsDao;
import com.pe.gidtec.servicedesk.ticket.historical.mapper.HistoricalMapper;
import com.pe.gidtec.servicedesk.ticket.historical.model.api.response.HistoricalGetResponse;
import com.pe.gidtec.servicedesk.ticket.historical.model.api.resquest.HistoricalPostRequest;
import com.pe.gidtec.servicedesk.ticket.historical.service.HistoricalsService;
import com.pe.gidtec.servicedesk.ticket.tickets.dao.TicketsDao;
import com.pe.gidtec.servicedesk.ticket.tickets.model.entity.TicketEntity;
import com.pe.gidtec.servicedesk.ticket.tickets.model.entity.UserTicket;
import com.pe.gidtec.servicedesk.ticket.user.dao.UsersDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HistoricalsServiceImpl  implements HistoricalsService {

    private final UsersDao usersDao;

    private final TicketsDao ticketsDao;

    private final HistoricalsDao historicalsDao;

    private final HistoricalMapper historicalMapper;

    @Override

    public Mono<ResultResponse<HistoricalGetResponse>> createHistorical(HistoricalPostRequest request, CommonHeaders headers) {
        return usersDao.getUserById(request.getUserReassignedId())
                .flatMap( userEntity -> ticketsDao.getTicketById(request.getTicketId())
                            .map(ticketEntity -> Tuples.of(userEntity,ticketEntity))
                )
                .map( tuple2 -> {
                    var ticketEntity = tuple2.getT2();
                    var userEntity = tuple2.getT1();
                    ticketEntity.setUserAssigned(UserTicket.builder()
                            .id(userEntity.getUserId())
                            .companyName(userEntity.getCompanyName())
                            .lastNames(userEntity.getLastNames())
                            .names(userEntity.getNames())
                            .build());
                    ticketEntity.getAudit().setLastModifiedBy(headers.getUserCode());
                    ticketEntity.getAudit().setLastModifiedDate(LocalDateTime.now());
                    return ticketEntity;
                })
                .flatMap(ticketsDao::saveTicket)
                .flatMap(ticketEntity -> historicalsDao.saveHistorical(historicalMapper.historicalPostRequestToEntity(request, headers))
                        .map(response ->  ResultResponse.ok(historicalMapper.entityToHistoricalResponse((response))))
                        .onErrorReturn(ResultResponse.error(ResponseStatus.ERROR_SAVE_HISTORICAL)));
    }

    @Override
    public Mono<ResultResponse<List<HistoricalGetResponse>>> getAllHistoricals() {
        return historicalsDao.findAll()
                .flatMap( historicalResponse -> ticketsDao.getTicketById(historicalResponse.getTicketId())
                            .map( ticketsResponse -> Tuples.of(historicalResponse,ticketsResponse))
                )
                .flatMap( tuple2 -> usersDao.getUserById(tuple2.getT1().getUserAssignedId())
                            .map(userAssignedResponse -> Tuples.of(tuple2.getT1(),tuple2.getT2(), userAssignedResponse))
                )
                .flatMap( tuple3 -> usersDao.getUserById(tuple3.getT1().getUserReassignedId())
                        .map(userReassignedResponse -> Tuples.of(tuple3.getT1(),tuple3.getT2(),tuple3.getT3(), userReassignedResponse))
                )
                .map(tuple4 -> historicalMapper.entityToHistoricalGetResponse(tuple4.getT1(), tuple4.getT2(),tuple4.getT3(),tuple4.getT4()))
                .collectList()
                .map( list-> {
                    if(list.isEmpty()){
                        return ResultResponse.ok(new ArrayList<>());
                    } else {
                        return ResultResponse.ok(list);
                    }
                });
    }
}
