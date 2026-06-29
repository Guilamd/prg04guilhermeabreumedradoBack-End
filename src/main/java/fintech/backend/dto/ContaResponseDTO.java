package fintech.backend.dto;

public class ContaResponseDTO {

    private Long id;

    private String descricao;

    private Double saldoAtual;

    private Boolean ativa;

    private Long usuarioId;

    private String usuarioNome;

    public ContaResponseDTO() {
    }

    public ContaResponseDTO(Long id, String descricao, Double saldoAtual, Boolean ativa, Long usuarioId, String usuarioNome) {
        this.id = id;
        this.descricao = descricao;
        this.saldoAtual = saldoAtual;
        this.ativa = ativa;
        this.usuarioId = usuarioId;
        this.usuarioNome = usuarioNome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }
}
