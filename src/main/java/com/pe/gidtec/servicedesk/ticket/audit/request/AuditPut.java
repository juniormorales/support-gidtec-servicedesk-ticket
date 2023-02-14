package com.pe.gidtec.servicedesk.ticket.audit.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "AuditPut")
public class AuditPut {

    @Schema(name = "statusCode",
            description = "El estado activo o inactivo del registro",
            example = "AC", pattern = "^(AC|IN)$")
    @NotNull(message = "El valor de 'statusCode' no debe ser vacio.")
    @Pattern(regexp = "^(AC|IN)$", message = "El valor de 'statusCode' admite solo los siguientes valores: AC y IN.")
    private String statusCode;
}
