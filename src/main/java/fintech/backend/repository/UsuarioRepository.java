package fintech.backend.repository;

import fintech.backend.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository responsavel pelas operacoes no banco relacionadas a Usuario.
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Page<Usuario> findAll(Pageable pageable);
}
