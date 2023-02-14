package com.pe.gidtec.servicedesk.ticket.util;

public class Constants {

    public static final String QUESTION_ONE = "1. ¿Nuestro equipo atendió su problema a tiempo?";
    public static final String QUESTION_TWO = "2. ¿Se logró solucionar su problema?";
    public static final String QUESTION_THREE = "3. ¿Cómo evaluaría nuestro servicio? Valore del 1 al 5";

    public static String getResponseBoolean(Boolean response){
        return response ? "Sí" : "No";
    }
}
