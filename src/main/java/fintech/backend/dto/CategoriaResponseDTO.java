package fintech.backend.dto;

public class CategoriaResponseDTO {

    private Long id;

    private String nome;

    private String corHexadecimal;

    private Boolean ativa;

    private Long usuarioId;

    private String usuarioNome;

    public CategoriaResponseDTO() {
    }

    public CategoriaResponseDTO(Long id, String nome, String corHexadecimal, Boolean ativa, Long usuarioId, String usuarioNome) {
        this.id = id;
        this.nome = nome;
        this.corHexadecimal = corHexadecimal;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCorHexadecimal() {
        return corHexadecimal;
    }

    public void setCorHexadecimal(String corHexadecimal) {
        this.corHexadecimal = corHexadecimal;
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
