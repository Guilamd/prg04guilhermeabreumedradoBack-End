package fintech.backend.transacao.dto;

import fintech.backend.transacao.entity.OrigemTransacao;
import fintech.backend.transacao.entity.StatusTransacao;
import fintech.backend.transacao.entity.TipoMovimentacao;
import fintech.backend.transacao.entity.TipoPagamento;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TransacaoResponseDTO {

    private Long id;

    private String titulo;

    private Double valor;

    private LocalDateTime dataHora;

    private OrigemTransacao origem;

    private TipoMovimentacao tipoMovimentacao;

    private TipoPagamento tipoPagamento;

    private StatusTransacao status;

    private Long contaId;

    private String contaDescricao;

    private Long categoriaId;

    private String categoriaNome;

    public TransacaoResponseDTO(Long id, String titulo, Double valor, LocalDateTime dataHora,
            OrigemTransacao origem, TipoMovimentacao tipoMovimentacao, TipoPagamento tipoPagamento,
            StatusTransacao status, Long contaId, String contaDescricao, Long categoriaId, String categoriaNome) {
        this.id = id;
        this.titulo = titulo;
        this.valor = valor;
        this.dataHora = dataHora;
        this.origem = origem;
        this.tipoMovimentacao = tipoMovimentacao;
        this.tipoPagamento = tipoPagamento;
        this.status = status;
        this.contaId = contaId;
        this.contaDescricao = contaDescricao;
        this.categoriaId = categoriaId;
        this.categoriaNome = categoriaNome;
    }
}
