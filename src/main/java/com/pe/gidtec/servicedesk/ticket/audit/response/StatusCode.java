package com.pe.gidtec.servicedesk.ticket.audit.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "StatusCode")
public class StatusCode {

    @Schema(name = "code",
            description = "Código de estado.",
            example = "AC")
    private String code;

    @Schema(name = "description",
            description = "Descripción de estado.",
            example = "Activo")
    private String description;

}
