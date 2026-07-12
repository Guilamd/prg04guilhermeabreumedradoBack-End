package fintech.backend.notificacao.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NotificacaoResponseDTO {

    private Long id;

    private String mensagem;

    private LocalDateTime dataEnvio;

    private Boolean lido;

    private Long usuarioId;

    private String usuarioNome;

    private Long metaOrcamentoId;

    private String metaOrcamentoMesAno;

    public NotificacaoResponseDTO(Long id, String mensagem, LocalDateTime dataEnvio, Boolean lido, Long usuarioId, String usuarioNome, Long metaOrcamentoId, String metaOrcamentoMesAno) {
        this.id = id;
        this.mensagem = mensagem;
        this.dataEnvio = dataEnvio;
        this.lido = lido;
        this.usuarioId = usuarioId;
        this.usuarioNome = usuarioNome;
        this.metaOrcamentoId = metaOrcamentoId;
        this.metaOrcamentoMesAno = metaOrcamentoMesAno;
    }
}
