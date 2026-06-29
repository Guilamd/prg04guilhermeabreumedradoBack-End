package fintech.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CategoriaRequestDTO {

    @NotBlank(message = "O nome e obrigatorio.")
    @Size(min = 2, max = 60, message = "O nome deve ter entre 2 e 60 caracteres.")
    private String nome;

    @NotBlank(message = "A cor hexadecimal e obrigatoria.")
    @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "A cor deve estar no formato #RRGGBB.")
    private String corHexadecimal;

    @NotNull(message = "O campo ativa e obrigatorio.")
    private Boolean ativa;

    @NotNull(message = "O id do usuario e obrigatorio.")
    private Long usuarioId;

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
}
