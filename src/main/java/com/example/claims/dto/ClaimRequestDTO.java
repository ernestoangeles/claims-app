package com.example.claims.dto;

import com.example.claims.validation.ValidClaimField;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClaimRequestDTO {
    @NotNull(message = "La empresa es requerida")
    @ValidClaimField(fieldType = "company", message = "Empresa inválida")
    private String company;

    @NotNull(message = "El motivo es requerido")
    @ValidClaimField(fieldType = "reason", message = "Motivo inválido")
    private String reason;

    @NotBlank(message = "La descripción es requerida")
    private String description;

    @NotBlank(message = "El correo del cliente es requerido")
    @Email(message = "Formato de correo inválido")
    private String email;
}