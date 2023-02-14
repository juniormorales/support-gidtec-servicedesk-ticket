package com.pe.gidtec.servicedesk.ticket.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QuestionnaireStatusEnum {

    PENDIENTE("PE","Pendiente"),
    COMPLETADO("CO","Completado");

    private final String code;
    private final String description;
}
