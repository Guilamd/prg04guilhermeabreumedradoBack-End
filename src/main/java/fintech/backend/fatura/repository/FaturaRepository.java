package fintech.backend.fatura.repository;

import fintech.backend.fatura.entity.Fatura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaturaRepository extends JpaRepository<Fatura, Long> {
    Page<Fatura> findByContaId(Long contaId, Pageable pageable);
}
