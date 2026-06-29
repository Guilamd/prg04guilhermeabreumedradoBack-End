package fintech.backend.dto;

import fintech.backend.entity.OrigemTransacao;
import fintech.backend.entity.StatusTransacao;
import fintech.backend.entity.TipoMovimentacao;
import fintech.backend.entity.TipoPagamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public OrigemTransacao getOrigem() {
        return origem;
    }

    public void setOrigem(OrigemTransacao origem) {
        this.origem = origem;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public StatusTransacao getStatus() {
        return status;
    }

    public void setStatus(StatusTransacao status) {
        this.status = status;
    }

    public Long getContaId() {
        return contaId;
    }

    public void setContaId(Long contaId) {
        this.contaId = contaId;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
