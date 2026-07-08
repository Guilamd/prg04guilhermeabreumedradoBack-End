package fintech.backend.transacao.dto;

import fintech.backend.transacao.entity.OrigemTransacao;
import fintech.backend.transacao.entity.StatusTransacao;
import fintech.backend.transacao.entity.TipoMovimentacao;
import fintech.backend.transacao.entity.TipoPagamento;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TransacaoRequestDTO {

    @NotBlank(message = "O titulo e obrigatorio.")
    @Size(min = 2, max = 100, message = "O titulo deve ter entre 2 e 100 caracteres.")
    private String titulo;

    @NotNull(message = "O valor e obrigatorio.")
    @Positive(message = "O valor deve ser maior que zero.")
    private Double valor;

    private LocalDateTime dataHora;

    @NotNull(message = "A origem da transacao e obrigatoria.")
    private OrigemTransacao origem;

    @NotNull(message = "O tipo de movimentacao e obrigatorio.")
    private TipoMovimentacao tipoMovimentacao;

    @NotNull(message = "O tipo de pagamento e obrigatorio.")
    private TipoPagamento tipoPagamento;

    @NotNull(message = "O status da transacao e obrigatorio.")
    private StatusTransacao status;

    @NotNull(message = "O id da conta e obrigatorio.")
    private Long contaId;

    @NotNull(message = "O id da categoria e obrigatorio.")
    private Long categoriaId;
}
