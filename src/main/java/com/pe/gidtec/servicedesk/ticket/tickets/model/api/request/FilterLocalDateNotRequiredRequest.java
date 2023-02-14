package com.pe.gidtec.servicedesk.ticket.tickets.model.api.request;

import com.pe.gidtec.servicedesk.lib.util.Patterns;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "FilterLocalDateNotRequiredRequest")
@Validated
public class FilterLocalDateNotRequiredRequest {

    @NotBlank(message = "El valor de 'startDate' no puede ser vacio.")
    @Schema(name = "startDate",
            description = "Fecha de inicio donde se consultará la cantidad de tickets creados",
            example = "2023-01-01", pattern = Patterns.LOCAL_DATE)
    @Pattern(regexp = Patterns.LOCAL_DATE, message = "El valor de 'startDate' debe ser del siguiente formato: 'YYYY-MM-DD'")
    private String startDate;

    @NotBlank(message = "El valor de 'endDate' no puede ser vacio.")
    @Schema(name = "endDate",
            description = "Fecha de fin donde se consultará la cantidad de tickets creados",
            example = "2023-01-26", pattern = Patterns.LOCAL_DATE)
    @Pattern(regexp = Patterns.LOCAL_DATE, message = "El valor de 'endDate' debe ser del siguiente formato: 'YYYY-MM-DD'")
    private String endDate;
}
