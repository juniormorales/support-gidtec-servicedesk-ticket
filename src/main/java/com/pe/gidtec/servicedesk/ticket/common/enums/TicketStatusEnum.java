package com.pe.gidtec.servicedesk.ticket.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TicketStatusEnum {

    RESUELTO("RE","RESUELTO"),
    CERRADO("CE","CERRADO"),
    EN_PROCESO("PR", "EN PROCESO"),
    ABIERTO("AB","ABIERTO"),
    CANCELADO("CA","CANCELADO");

    private final String code;
    private final String description;
}
