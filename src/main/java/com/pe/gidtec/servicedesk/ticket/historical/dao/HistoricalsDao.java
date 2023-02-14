package com.pe.gidtec.servicedesk.ticket.historical.dao;

import com.pe.gidtec.servicedesk.ticket.historical.model.entity.HistoricalEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HistoricalsDao {

    Mono<HistoricalEntity> saveHistorical(HistoricalEntity entity);

    Flux<HistoricalEntity> findAll();
}
