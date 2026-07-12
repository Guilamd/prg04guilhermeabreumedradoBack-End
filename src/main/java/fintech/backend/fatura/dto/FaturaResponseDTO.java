package fintech.backend.fatura.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class FaturaResponseDTO {

    private Long id;

    private String mesAnoReferencia;

    private Double valorTotal;

    private LocalDate dataVencimento;

    private Boolean estaPaga;

    private Long contaId;

    private String contaDescricao;

    public FaturaResponseDTO(Long id, String mesAnoReferencia, Double valorTotal, LocalDate dataVencimento,
            Boolean estaPaga, Long contaId, String contaDescricao) {
        this.id = id;
        this.mesAnoReferencia = mesAnoReferencia;
        this.valorTotal = valorTotal;
        this.dataVencimento = dataVencimento;
        this.estaPaga = estaPaga;
        this.contaId = contaId;
        this.contaDescricao = contaDescricao;
    }
}
