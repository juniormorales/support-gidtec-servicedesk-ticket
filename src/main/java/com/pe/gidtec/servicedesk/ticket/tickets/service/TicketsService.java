package com.pe.gidtec.servicedesk.ticket.tickets.service;

import com.pe.gidtec.servicedesk.ticket.common.model.api.response.ResultResponse;
import com.pe.gidtec.servicedesk.ticket.common.model.header.CommonHeaders;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.request.*;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.response.FilterLocalDateResponse;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.response.NumberTicketsClassifiedResponse;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.response.TicketDetailsResponse;
import com.pe.gidtec.servicedesk.ticket.tickets.model.api.response.TicketResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface TicketsService {

    Mono<ResultResponse<List<TicketResponse>>> getAllTicketsByFilter(FilterTicketsRequest request);

    Mono<ResultResponse<TicketResponse>> createTicket(TicketPostRequest request, CommonHeaders headers);

    Mono<ResultResponse<TicketResponse>> deleteTicket(String ticketId, CommonHeaders headers);

    Mono<ResultResponse<TicketResponse>> updateStatusCodeTicket(StatusCodeTicketPutRequest request, CommonHeaders headers);

    Mono<ResultResponse<TicketResponse>> updateTypeCodeTicket(TypeCodeTicketPutRequest request, CommonHeaders headers);

    Mono<ResultResponse<TicketResponse>> setUserAssignedToTicket(UserAssignedPostRequest request, CommonHeaders headers);

    Mono<ResultResponse<NumberTicketsClassifiedResponse>> getNumberOfTypesTicketOpened();

    Mono<ResultResponse<List<FilterLocalDateResponse>>> getNumberTicketsByDateCreatedAndUserNullable(FilterLocalDateRequest request);

    Mono<ResultResponse<TicketDetailsResponse>> getTicketDetails(String ticketId);

    Mono<byte[]> generateReportTicketsByDateCreatedAndUserNullable(FilterLocalDateRequest request);
}
