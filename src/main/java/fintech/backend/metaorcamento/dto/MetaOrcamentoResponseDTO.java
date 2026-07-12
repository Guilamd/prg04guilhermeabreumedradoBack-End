package fintech.backend.metaorcamento.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MetaOrcamentoResponseDTO {

    private Long id;

    private Double valorLimite;

    private String mesAnoReferencia;

    private Long categoriaId;

    private String categoriaNome;

    public MetaOrcamentoResponseDTO(Long id, Double valorLimite, String mesAnoReferencia, Long categoriaId, String categoriaNome) {
        this.id = id;
        this.valorLimite = valorLimite;
        this.mesAnoReferencia = mesAnoReferencia;
        this.categoriaId = categoriaId;
        this.categoriaNome = categoriaNome;
    }
}
