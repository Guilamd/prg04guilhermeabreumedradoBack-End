package fintech.backend.conta.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContaResponseDTO {

    private Long id;

    private String descricao;

    private Double saldoAtual;

    private Boolean ativa;

    private Long usuarioId;

    private String usuarioNome;

    private Long instituicaoFinanceiraId;

    private String instituicaoFinanceiraNome;

    public ContaResponseDTO(Long id, String descricao, Double saldoAtual, Boolean ativa, Long usuarioId, String usuarioNome, Long instituicaoFinanceiraId, String instituicaoFinanceiraNome) {
        this.id = id;
        this.descricao = descricao;
        this.saldoAtual = saldoAtual;
        this.ativa = ativa;
        this.usuarioId = usuarioId;
        this.usuarioNome = usuarioNome;
        this.instituicaoFinanceiraId = instituicaoFinanceiraId;
        this.instituicaoFinanceiraNome = instituicaoFinanceiraNome;
    }
}
