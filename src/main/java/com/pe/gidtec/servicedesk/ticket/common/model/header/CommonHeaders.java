package com.pe.gidtec.servicedesk.ticket.common.model.header;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(name = "CommonHeaders")
public class CommonHeaders {

    @JsonProperty("user-code")
    @Size(max = 50, message = "La cabecera user-code debe ser maximo 50 caracteres")
    @Parameter(in = ParameterIn.HEADER,
            description = "Codigo de usuario",
            name = "user-code",
            example = "T123587",
            required = true)
    @NotBlank(message = "La cabecera user-code es obligatorio")
    @Pattern(regexp = "^[\\w\\s]+$", message = "La cabecera user-code debe ser alfanumérica")
    private String userCode;
}
