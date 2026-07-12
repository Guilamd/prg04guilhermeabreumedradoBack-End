package fintech.backend.fatura.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class FaturaRequestDTO {

    @NotBlank(message = "O mês/ano de referência é obrigatório.")
    private String mesAnoReferencia;

    @NotNull(message = "O valor total é obrigatório.")
    @PositiveOrZero(message = "O valor total deve ser zero ou maior.")
    private Double valorTotal;

    @NotNull(message = "A data de vencimento é obrigatória.")
    private LocalDate dataVencimento;

    @NotNull(message = "O campo estaPaga é obrigatório.")
    private Boolean estaPaga;

    @NotNull(message = "O id da conta é obrigatório.")
    private Long contaId;
}
