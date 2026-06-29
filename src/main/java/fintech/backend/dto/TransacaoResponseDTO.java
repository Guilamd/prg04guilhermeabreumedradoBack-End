package fintech.backend.dto;

import fintech.backend.entity.OrigemTransacao;
import fintech.backend.entity.StatusTransacao;
import fintech.backend.entity.TipoMovimentacao;
import fintech.backend.entity.TipoPagamento;
import java.time.LocalDateTime;

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

    public TransacaoResponseDTO() {
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getContaDescricao() {
        return contaDescricao;
    }

    public void setContaDescricao(String contaDescricao) {
        this.contaDescricao = contaDescricao;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }
}
