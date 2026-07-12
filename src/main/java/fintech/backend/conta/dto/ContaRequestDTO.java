package fintech.backend.conta.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
public class ContaRequestDTO {

    @NotBlank(message = "A descricao e obrigatoria.")
    @Size(min = 2, max = 100, message = "A descricao deve ter entre 2 e 100 caracteres.")
    private String descricao;

    @NotNull(message = "O saldo atual e obrigatorio.")
    private Double saldoAtual;

    @NotNull(message = "O campo ativa e obrigatorio.")
    private Boolean ativa;

    @NotNull(message = "O id do usuario e obrigatorio.")
    private Long usuarioId;

    private Long instituicaoFinanceiraId;
}
