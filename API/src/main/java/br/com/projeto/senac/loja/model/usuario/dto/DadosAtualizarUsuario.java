package br.com.projeto.senac.loja.model.usuario.dto;

import br.com.projeto.senac.loja.model.usuario.Grupo;
import br.com.projeto.senac.loja.model.usuario.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarUsuario (
        @NotNull
        Long id,
        @NotBlank       //---->  Verifica se o campo está sem texto ou nulo, serve apenas para Strings
        String nome,
        @NotBlank
        String cpf,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha,
        @NotNull
        Grupo grupo,
        @NotNull
        Status status
) {
}
