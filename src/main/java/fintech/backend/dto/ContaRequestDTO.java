package fintech.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ContaRequestDTO {

    @NotBlank(message = "A descricao e obrigatoria.")
    @Size(min = 2, max = 100, message = "A descricao deve ter entre 2 e 100 caracteres.")
    private String descricao;

    @NotNull(message = "O saldo atual e obrigatorio.")
    private Double saldoAtual;

    @NotNull(message = "O campo ativa e obrigatorio.")
    private Boolean ativa;

    @NotNull(message = "O id do usuario e obrigatorio.")
    private Long usuarioId;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(Double saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
