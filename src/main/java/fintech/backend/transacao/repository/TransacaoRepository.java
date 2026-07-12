package fintech.backend.transacao.repository;

import fintech.backend.transacao.entity.Transacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    Page<Transacao> findByContaId(Long contaId, Pageable pageable);
    
    @Query("SELECT COALESCE(SUM(t.valor), 0.0) FROM Transacao t WHERE t.conta.id = :contaId AND t.dataHora BETWEEN :inicio AND :fim AND t.tipoMovimentacao = fintech.backend.transacao.entity.TipoMovimentacao.RECEITA")
    Double somarReceitas(@Param("contaId") Long contaId, @Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);

    @Query("SELECT COALESCE(SUM(t.valor), 0.0) FROM Transacao t WHERE t.conta.id = :contaId AND t.dataHora BETWEEN :inicio AND :fim AND t.tipoMovimentacao = fintech.backend.transacao.entity.TipoMovimentacao.DESPESA")
    Double somarDespesas(@Param("contaId") Long contaId, @Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);

    @Query("SELECT t.categoria.nome, COALESCE(SUM(t.valor), 0.0) FROM Transacao t WHERE t.conta.id = :contaId AND t.dataHora BETWEEN :inicio AND :fim AND t.tipoMovimentacao = fintech.backend.transacao.entity.TipoMovimentacao.DESPESA GROUP BY t.categoria.nome")
    List<Object[]> agruparGastosPorCategoria(@Param("contaId") Long contaId, @Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
}
