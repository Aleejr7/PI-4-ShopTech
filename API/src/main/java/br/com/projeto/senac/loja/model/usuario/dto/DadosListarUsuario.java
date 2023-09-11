package br.com.projeto.senac.loja.model.usuario.dto;

import br.com.projeto.senac.loja.model.usuario.Grupo;
import br.com.projeto.senac.loja.model.usuario.Status;
import br.com.projeto.senac.loja.model.usuario.database.Usuario;

public record DadosListarUsuario(Long id, String nome, String email, Status status, Grupo grupo) {

    public DadosListarUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getStatus(), usuario.getGrupo());
    }
}
