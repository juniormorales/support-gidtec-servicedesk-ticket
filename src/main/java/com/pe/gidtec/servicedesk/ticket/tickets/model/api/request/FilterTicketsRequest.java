package com.pe.gidtec.servicedesk.ticket.tickets.model.api.request;

import com.pe.gidtec.servicedesk.lib.util.Patterns;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "FilterTicketsRequest")
public class FilterTicketsRequest {

    @Schema(name = "userAssignedId",
            description = "El identificador del usuario al cual se le asigno el ticket",
            example = "aabC14T00abxx1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String userAssignedId;

    @Schema(name = "userCreatorId",
            description = "El identificador del usuario el cual creó el ticket",
            example = "aabC24T00abxx1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String userCreatorId;

    @Pattern(regexp = "^(RE|CE|AB)$", message = "El valor de 'statusCode' admite solo los siguientes valores: RE, CE y AB.")
    @Schema(name = "statusCode",
            description = "Codigo del estado actual del ticket",
            example = "AB", pattern = "^(RE|CE|AB)$",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String statusCode;

    @Pattern(regexp = "^(CO|RE|IN)$", message = "El valor de 'typeCode' admite solo los siguientes valores: CO, RE y IN.")
    @Schema(name = "typeCode",
            description = "Codigo del tipo de ticket",
            example = "CO", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String typeCode;

    @Schema(name = "filterLocalDate",
            description = "Filtro que indica un rango de fecha en el que se listará los tickets")
    @Valid
    private FilterLocalDateNotRequiredRequest filterLocalDate;
}
