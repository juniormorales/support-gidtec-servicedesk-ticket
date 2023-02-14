package com.pe.gidtec.servicedesk.ticket.tickets.model.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name= "UserTicketResponse")
public class UserTicketResponse {

    @Schema(name = "id",
            description = "Identificador del usuario que generó el ticket",
            example = "1ab24sof5e1vxc")
    private String id;

    @Schema(name = "names",
            description = "Nombres del usuario que generó el ticket",
            example = "Kimberly Jazmin")
    private String names;

    @Schema(name = "lastNames",
            description = "Apellidos del usuario que generó el ticket",
            example = "Japay Rojas")
    private String lastNames;

    @Schema(name = "companyName",
            description = "Nombre de la compañía a la que pertenece el usuario que generó el ticket",
            example = "Latina PE")
    private String companyName;
}
