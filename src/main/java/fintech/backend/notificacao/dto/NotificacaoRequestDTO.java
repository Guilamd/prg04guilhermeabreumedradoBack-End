package fintech.backend.notificacao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NotificacaoRequestDTO {

    @NotBlank(message = "A mensagem é obrigatória.")
    private String mensagem;

    @NotNull(message = "A data de envio é obrigatória.")
    private LocalDateTime dataEnvio;

    @NotNull(message = "O campo lido é obrigatório.")
    private Boolean lido;

    @NotNull(message = "O id do usuário é obrigatório.")
    private Long usuarioId;

    private Long metaOrcamentoId;
}

