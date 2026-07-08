package fintech.backend.usuario.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

// DTO usado para devolver dados do usuario sem expor informacoes sensiveis, como a senha.
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class UsuarioResponseDTO {

    private Long id;

    private String nome;

    private String email;

    private LocalDateTime dataCriacao;

    public UsuarioResponseDTO(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public UsuarioResponseDTO(Long id, String nome, String email, LocalDateTime dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataCriacao = dataCriacao;
    }
}
