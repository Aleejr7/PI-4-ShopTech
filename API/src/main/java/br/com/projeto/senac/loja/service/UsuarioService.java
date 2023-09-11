package br.com.projeto.senac.loja.service;

import br.com.projeto.senac.loja.model.usuario.database.Usuario;
import br.com.projeto.senac.loja.model.usuario.database.UsuarioRepository;
import br.com.projeto.senac.loja.model.usuario.dto.DadosAtualizarUsuario;
import br.com.projeto.senac.loja.model.usuario.dto.DadosCadastroUsuario;
import br.com.projeto.senac.loja.model.usuario.dto.DadosListarUsuario;
import br.com.projeto.senac.loja.model.usuario.dto.DadosLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public void salvarUsuario(DadosCadastroUsuario dadosCadastroUsuario){
        repository.save(new Usuario(dadosCadastroUsuario));
    }
    public List<DadosListarUsuario> listarUsuarios(){
        return repository.findAll().stream().map(DadosListarUsuario::new).toList();
    }
    public void atualizarUsuario(DadosAtualizarUsuario dadosAtualizarUsuario){
        Usuario usuario = repository.getReferenceById(dadosAtualizarUsuario.id());
        usuario = atualizarInformacoes(dadosAtualizarUsuario, usuario);
        repository.save(usuario);
    }
    public Usuario logarUsuario(DadosLogin dadosLogin){
        Usuario usuario;
        if(dadosLogin.grupo().toString().equals("ADMIN")){
            return logarAdmin(dadosLogin);
        }else{
            return logarEstoquista(dadosLogin);
        }
    }

    public Usuario atualizarInformacoes(DadosAtualizarUsuario dadosAtualizarUsuario, Usuario usuario){
        if(dadosAtualizarUsuario.nome() != null){
            usuario.setNome(dadosAtualizarUsuario.nome());
        }
        if(dadosAtualizarUsuario.cpf() != null){
            usuario.setCpf(dadosAtualizarUsuario.cpf());
        }
        if(dadosAtualizarUsuario.email() != null){
            usuario.setEmail(dadosAtualizarUsuario.email());
        }
        if(dadosAtualizarUsuario.senha() != null){
            usuario.setSenha(dadosAtualizarUsuario.senha());
        }
        if(dadosAtualizarUsuario.grupo() != null){
            usuario.setGrupo(dadosAtualizarUsuario.grupo());
        }
        if(dadosAtualizarUsuario.status() != null){
            usuario.setStatus(dadosAtualizarUsuario.status());
        }
        return usuario;
    }

    public Usuario logarAdmin(DadosLogin dadosLogin){
        String email = dadosLogin.email();
        String senha = dadosLogin.senha();
        String grupo = dadosLogin.grupo().toString();
        Usuario usuario = repository.findByEmailAndPasswordAndGrupo(email, senha, grupo);
        if(usuario != null){
            if(usuario.getStatus().toString().equals("INATIVO")){
                throw new RuntimeException("Administrador está inativo!");
            }
        }
        return usuario;
    }
    public Usuario logarEstoquista(DadosLogin dadosLogin){
        String email = dadosLogin.email();
        String senha = dadosLogin.senha();
        String grupo = dadosLogin.grupo().toString();
        Usuario usuario = repository.findByEmailAndPasswordAndGrupo(email, senha, grupo);
        if(usuario != null){
            if(usuario.getStatus().toString().equals("INATIVO")){
                throw new RuntimeException("Estoquista está inativo!");
            }
        }
        return usuario;
    }
}

