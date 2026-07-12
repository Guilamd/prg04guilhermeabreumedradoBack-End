package fintech.backend.instituicaofinanceira.repository;

import fintech.backend.instituicaofinanceira.entity.IstituicaoFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IstituicaoFinanceiraRepository extends JpaRepository<IstituicaoFinanceira, Long> {
}

