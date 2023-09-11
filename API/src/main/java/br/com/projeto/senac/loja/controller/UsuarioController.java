package br.com.projeto.senac.loja.controller;

import br.com.projeto.senac.loja.model.usuario.dto.DadosAtualizarUsuario;
import br.com.projeto.senac.loja.model.usuario.dto.DadosCadastroUsuario;
import br.com.projeto.senac.loja.model.usuario.dto.DadosListarUsuario;
import br.com.projeto.senac.loja.model.usuario.database.Usuario;
import br.com.projeto.senac.loja.model.usuario.dto.DadosLogin;
import br.com.projeto.senac.loja.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarNovoUsuario(@RequestBody @Valid DadosCadastroUsuario dadosCadastroUsuario) {
        service.salvarUsuario(dadosCadastroUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<DadosListarUsuario>> listarUsuarios() {
        List<DadosListarUsuario> usuarios = service.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizarUsuario(@RequestBody @Valid DadosAtualizarUsuario dadosAtualizarUsuario) {
        try {
            service.atualizarUsuario(dadosAtualizarUsuario);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Falha na atualização do usuário" + dadosAtualizarUsuario.nome() + ": " + e.getMessage());
        }
    }


    @PostMapping("/login")
    @Transactional
    public ResponseEntity<Usuario> logarUsuario(@RequestBody @Valid DadosLogin dadosLogin) {
        Usuario usuario = service.logarUsuario(dadosLogin);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
