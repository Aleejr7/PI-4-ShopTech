package br.com.projeto.senac.loja.model.usuario.dto;

import br.com.projeto.senac.loja.model.usuario.Grupo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//Esses dados/atributos do record que vamos receber como JSON na Controller. *REPRESENTA O DTO*
//Estou usando beanvalidation para validar os campos nulos
public record DadosCadastroUsuario(
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
        Grupo grupo) {
}
