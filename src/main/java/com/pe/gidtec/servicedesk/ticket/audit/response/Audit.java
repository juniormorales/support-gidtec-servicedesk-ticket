package com.pe.gidtec.servicedesk.ticket.audit.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Audit")
public class Audit {

    @Schema(name = "status",
            description = "El estado activo o inactivo del registro")
    private StatusCode status;

    @Schema(name = "deleted",
            description = "Identificador de eliminacion",
            example = "false")
    private Boolean deleted;

    @Schema(name = "createdDate",
            description = "Fecha y hora de creacion.",
            example = "2022-08-15T20:00:00")
    private String createdDate;

    @Schema(name = "createdBy",
            description = "Informacion de creacion",
            example = "T58974")
    private String createdBy;

    @Schema(name = "lastModifiedDate",
            description = "Fecha y hora de ultima actualizacion",
            example = "2022-08-15T20:00:00")
    private String lastModifiedDate;

    @Schema(name = "lastModifiedBy",
            description = "Informacion de actualizacion",
            example = "T12587")
    private String lastModifiedBy;
}
