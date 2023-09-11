package br.com.projeto.senac.loja.model.usuario.dto;

import br.com.projeto.senac.loja.model.usuario.Grupo;
import br.com.projeto.senac.loja.model.usuario.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosLogin(
        @NotBlank
        String email,
        @NotBlank
        String senha,
        @NotNull
        @Enumerated(EnumType.STRING)
        Grupo grupo
) {
}
