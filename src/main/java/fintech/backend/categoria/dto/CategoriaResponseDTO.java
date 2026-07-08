package fintech.backend.categoria.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoriaResponseDTO {

    private Long id;

    private String nome;

    private String corHexadecimal;

    private Boolean ativa;

    private Long usuarioId;

    private String usuarioNome;

    public CategoriaResponseDTO(Long id, String nome, String corHexadecimal, Boolean ativa, Long usuarioId, String usuarioNome) {
        this.id = id;
        this.nome = nome;
        this.corHexadecimal = corHexadecimal;
        this.ativa = ativa;
        this.usuarioId = usuarioId;
        this.usuarioNome = usuarioNome;
    }
}
