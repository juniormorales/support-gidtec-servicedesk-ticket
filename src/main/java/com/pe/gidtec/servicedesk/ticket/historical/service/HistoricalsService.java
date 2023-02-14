package com.pe.gidtec.servicedesk.ticket.historical.service;

import com.pe.gidtec.servicedesk.ticket.common.model.api.response.ResultResponse;
import com.pe.gidtec.servicedesk.ticket.common.model.header.CommonHeaders;
import com.pe.gidtec.servicedesk.ticket.historical.model.api.response.HistoricalGetResponse;
import com.pe.gidtec.servicedesk.ticket.historical.model.api.resquest.HistoricalPostRequest;
import reactor.core.publisher.Mono;

import java.util.List;

public interface HistoricalsService {

    Mono<ResultResponse<HistoricalGetResponse>> createHistorical(HistoricalPostRequest request, CommonHeaders headers);

    Mono<ResultResponse<List<HistoricalGetResponse>>> getAllHistoricals();
}
