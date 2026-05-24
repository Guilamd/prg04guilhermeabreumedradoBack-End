package fintech.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// DTO usado para devolver dados do usuario sem expor informacoes sensiveis, como a senha.
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioResponseDTO {

    private Long id;

    private String nome;

    private String email;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
