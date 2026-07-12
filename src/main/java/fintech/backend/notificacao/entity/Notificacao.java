package fintech.backend.notificacao.entity;

import fintech.backend.metaorcamento.entity.MetaOrcamento;
import fintech.backend.usuario.entity.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificacoes")
@Data
@NoArgsConstructor
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;

    private LocalDateTime dataEnvio;

    private Boolean lido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meta_orcamento_id")
    private MetaOrcamento metaOrcamento;

    public Notificacao(Long id, String mensagem, LocalDateTime dataEnvio, Boolean lido, Usuario usuario, MetaOrcamento metaOrcamento) {
        this.id = id;
        this.mensagem = mensagem;
        this.dataEnvio = dataEnvio;
        this.lido = lido;
        this.usuario = usuario;
        this.metaOrcamento = metaOrcamento;
    }
}
