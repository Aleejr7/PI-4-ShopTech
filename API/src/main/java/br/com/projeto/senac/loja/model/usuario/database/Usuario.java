package br.com.projeto.senac.loja.model.usuario.database;

import br.com.projeto.senac.loja.model.usuario.Grupo;
import br.com.projeto.senac.loja.model.usuario.Status;
import br.com.projeto.senac.loja.model.usuario.dto.DadosAtualizarUsuario;
import br.com.projeto.senac.loja.model.usuario.dto.DadosCadastroUsuario;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Column(name = "ID")
    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CPF")
    private String cpf;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "GRUPO")
    @Enumerated(EnumType.STRING)
    private Grupo grupo;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "SENHA")
    private String senha;
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Usuario(DadosCadastroUsuario dadosCadastroUsuario) {
        this.nome = dadosCadastroUsuario.nome();
        this.cpf = dadosCadastroUsuario.cpf();
        this.email = dadosCadastroUsuario.email();
        this.senha = dadosCadastroUsuario.senha();
        this.grupo = dadosCadastroUsuario.grupo();
        this.status = Status.ATIVO;
    }
}
