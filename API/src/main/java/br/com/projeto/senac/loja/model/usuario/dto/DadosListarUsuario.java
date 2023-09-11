package br.com.projeto.senac.loja.model.usuario.dto;

import br.com.projeto.senac.loja.model.usuario.Grupo;
import br.com.projeto.senac.loja.model.usuario.Status;
import br.com.projeto.senac.loja.model.usuario.database.Usuario;

public record DadosListarUsuario(Long id, String nome, String cpf, String email, String senha, Grupo grupo, Status status) {

    public DadosListarUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getEmail(), usuario.getSenha(), usuario.getGrupo(), usuario.getStatus());
    }
}
