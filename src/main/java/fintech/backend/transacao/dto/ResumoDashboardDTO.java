package fintech.backend.transacao.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@NoArgsConstructor
public class ResumoDashboardDTO {

    private String mesAno;

    private Long contaId;

    private Double totalReceitas;

    private Double totalDespesas;

    private Double saldoFinal;

    private Map<String, Double> gastosPorCategoria;

    public ResumoDashboardDTO(String mesAno, Long contaId, Double totalReceitas, Double totalDespesas, Map<String, Double> gastosPorCategoria) {
        this.mesAno = mesAno;
        this.contaId = contaId;
        this.totalReceitas = totalReceitas;
        this.totalDespesas = totalDespesas;
        this.saldoFinal = totalReceitas - totalDespesas;
        this.gastosPorCategoria = gastosPorCategoria;
    }
}
