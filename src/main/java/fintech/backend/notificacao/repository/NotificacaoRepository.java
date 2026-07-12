package fintech.backend.notificacao.repository;

import fintech.backend.notificacao.entity.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
}

