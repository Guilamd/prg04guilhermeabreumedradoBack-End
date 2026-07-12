package fintech.backend.metaorcamento.repository;

import fintech.backend.metaorcamento.entity.MetaOrcamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaOrcamentoRepository extends JpaRepository<MetaOrcamento, Long> {
}
