package com.pe.gidtec.servicedesk.ticket.historical.repository;

import com.pe.gidtec.servicedesk.ticket.historical.model.entity.HistoricalEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface HistoricalsRepository extends ReactiveMongoRepository<HistoricalEntity, String> {

    Flux<HistoricalEntity> findAllByAuditDeletedFalseOrderByAuditCreatedDateAsc();
}
