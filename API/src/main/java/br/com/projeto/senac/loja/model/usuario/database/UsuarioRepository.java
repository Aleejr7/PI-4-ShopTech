package br.com.projeto.senac.loja.model.usuario.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query(value = "SELECT * FROM USUARIOS WHERE EMAIL = :email AND SENHA = :senha AND GRUPO = :grupo", nativeQuery = true)
    Usuario findByEmailAndPasswordAndGrupo(@Param("email") String email, @Param("senha") String senha, @Param("grupo") String grupo);
}
