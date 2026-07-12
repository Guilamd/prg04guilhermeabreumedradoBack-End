package fintech.backend.metaorcamento.entity;

import fintech.backend.categoria.entity.Categoria;
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

@Entity
@Table(name = "metas_orcamento")
@Data
@NoArgsConstructor
public class MetaOrcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorLimite;

    private String mesAnoReferencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    public MetaOrcamento(Long id, Double valorLimite, String mesAnoReferencia, Categoria categoria) {
        this.id = id;
        this.valorLimite = valorLimite;
        this.mesAnoReferencia = mesAnoReferencia;
        this.categoria = categoria;
    }
}
