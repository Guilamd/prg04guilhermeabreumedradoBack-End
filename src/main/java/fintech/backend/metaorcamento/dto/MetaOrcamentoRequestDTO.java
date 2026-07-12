package fintech.backend.metaorcamento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MetaOrcamentoRequestDTO {

    @NotNull(message = "O valor limite é obrigatório.")
    @Positive(message = "O valor limite deve ser maior que zero.")
    private Double valorLimite;

    @NotBlank(message = "O mês/ano de referência é obrigatório.")
    private String mesAnoReferencia;

    @NotNull(message = "O id da categoria é obrigatório.")
    private Long categoriaId;
}
