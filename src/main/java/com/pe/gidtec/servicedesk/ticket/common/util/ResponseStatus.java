package com.pe.gidtec.servicedesk.ticket.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {

    OK("00", "la solicitud ha tenido éxito."),
    NO_DATA("00", "No hay registros para la busqueda seleccionada"),
    ERROR_SAVE_TICKET("01", "Ocurrió un error al intentar guardar el ticket."),
    BAD_ID_TICKET_NOT_EXISTS("02", "El identificador del ticket proporcionado no existe"),
    ERROR_GET_TICKET("03","Ocurrió un error al intentar listar los tickets"),
    METHOD_NOT_IMPLEMENTED("20", "El metodo Http ingresado no se encuentra implementado"),
    ERROR_SAVE_MESSAGE("01", "Ocurrió un error al intentar guardar el mensaje de respuesta"),
    BAD_ID_MESSAGE_NOT_EXISTS("02", "El identificador del mensaje proporcionado no existe"),
    ERROR_GET_MESSAGE("03","Ocurrió un error al intentar listar los mensajes de respuesta del ticket"),
    ERROR_SAVE_HISTORICAL("01","Ocurrió un error al intentar guardar el historico"),
    ERROR_GET_HISTORICALS("03","Ocurrió un error al intentar listar los historicos de casos de tickets"),
    ERROR_SAVE_QUESTIONNAIRE("01", "Ocurrió un error al intentar guardar las respuestas del cuestionario."),
    BAD_ID_TICKET_QUESTIONNAIRE_ALREADY_EXISTS("03", "Ya existe una encuesta asociada a este ticket"),
    BAD_ID_QUESTIONNAIRE_NOT_EXISTS("02","El identificador de la encuesta proporcionado no existe");


    private final String code;
    private final String description;
}
