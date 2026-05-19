package fintech.backend.repository;

import fintech.backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository responsavel pelas operacoes no banco relacionadas a Usuario.
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
