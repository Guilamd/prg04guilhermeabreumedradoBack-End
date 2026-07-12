package fintech.backend.instituicaofinanceira.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IstituicaoFinanceiraRequestDTO {

    @NotBlank(message = "O nome do banco é obrigatório.")
    private String nomeBanco;

    @NotBlank(message = "O código de compensação é obrigatório.")
    private String codigoCompensacao;
}

