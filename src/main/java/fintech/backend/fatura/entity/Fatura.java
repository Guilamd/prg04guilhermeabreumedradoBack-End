package fintech.backend.fatura.entity;

import fintech.backend.conta.entity.Conta;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "faturas")
@Data
@NoArgsConstructor
public class Fatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mesAnoReferencia;

    private Double valorTotal;

    private LocalDate dataVencimento;

    private Boolean estaPaga;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

    public Fatura(Long id, String mesAnoReferencia, Double valorTotal, LocalDate dataVencimento, Boolean estaPaga, Conta conta) {
        this.id = id;
        this.mesAnoReferencia = mesAnoReferencia;
        this.valorTotal = valorTotal;
        this.dataVencimento = dataVencimento;
        this.estaPaga = estaPaga;
        this.conta = conta;
    }
}
