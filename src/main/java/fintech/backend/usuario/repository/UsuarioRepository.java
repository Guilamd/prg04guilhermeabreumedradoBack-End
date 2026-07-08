package fintech.backend.usuario.repository;

import fintech.backend.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository responsavel pelas operacoes no banco relacionadas a Usuario.
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
