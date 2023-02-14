package com.pe.gidtec.servicedesk.ticket.user.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "UserRequest")
public class UserRequest {

    @NotBlank(message = "El valor de 'id' no puede ser vacio.")
    @Schema(name = "id",
            description = "Identificador del usuario",
            example = "1ab24sof5e1vxc")
    private String id;

    @NotBlank(message = "El valor de 'names' no puede ser vacio.")
    @Schema(name = "names",
            description = "Nombres del usuario",
            example = "Kimberly Jazmin")
    private String names;

    @NotBlank(message = "El valor de 'lastNames' no puede ser vacio.")
    @Schema(name = "lastNames",
            description = "Apellidos del usuario",
            example = "Japay Rojas")
    private String lastNames;

    @NotBlank(message = "El valor de 'companyName' no puede ser vacio.")
    @Schema(name = "companyName",
            description = "Nombre de la compañía a la que pertenece el usuario",
            example = "Latina PE")
    private String companyName;
}
