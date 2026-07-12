package fintech.backend.instituicaofinanceira.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IstituicaoFinanceiraResponseDTO {

    private Long id;

    private String nomeBanco;

    private String codigoCompensacao;

    public IstituicaoFinanceiraResponseDTO(Long id, String nomeBanco, String codigoCompensacao) {
        this.id = id;
        this.nomeBanco = nomeBanco;
        this.codigoCompensacao = codigoCompensacao;
    }
}
