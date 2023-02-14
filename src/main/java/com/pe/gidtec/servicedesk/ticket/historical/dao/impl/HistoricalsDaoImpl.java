package com.pe.gidtec.servicedesk.ticket.historical.dao.impl;

import com.pe.gidtec.servicedesk.ticket.historical.dao.HistoricalsDao;
import com.pe.gidtec.servicedesk.ticket.historical.model.entity.HistoricalEntity;
import com.pe.gidtec.servicedesk.ticket.historical.repository.HistoricalsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class HistoricalsDaoImpl implements HistoricalsDao{

    private final HistoricalsRepository historicalsRepository;


    @Override
    public Mono<HistoricalEntity> saveHistorical(HistoricalEntity entity) {
        return historicalsRepository.save(entity)
                .doOnSuccess(message -> log.info("Historico " + message.toString() + " guardado con éxito."))
                .onErrorResume( ex -> Mono.error(new Exception("Ocurrio un error al intentar guardar el historico")));
    }

    @Override
    public Flux<HistoricalEntity> findAll() {
        return historicalsRepository.findAllByAuditDeletedFalseOrderByAuditCreatedDateAsc();
    }
}
