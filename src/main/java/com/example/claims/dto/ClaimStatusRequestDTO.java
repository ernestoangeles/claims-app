package com.example.claims.dto;

import com.example.claims.validation.ValidClaimField;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClaimStatusRequestDTO {
    @NotNull(message = "El estado es requerido")
    @ValidClaimField(fieldType = "status", message = "Estado inválido")
    private String status;

    @NotBlank(message = "El comentario es requerido")
    private String agentComment;

    @NotBlank(message = "El correo del agente es requerido")
    @Email(message = "Formato de correo inválido")
    private String agentEmail;
}