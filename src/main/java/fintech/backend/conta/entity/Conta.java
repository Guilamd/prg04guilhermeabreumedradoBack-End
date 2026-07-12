package fintech.backend.conta.entity;

import fintech.backend.instituicaofinanceira.entity.IstituicaoFinanceira;
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

@Entity
@Table(name = "contas")
@Data
@NoArgsConstructor
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private Double saldoAtual;

    private Boolean ativa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instituicao_financeira_id")
    private IstituicaoFinanceira instituicaoFinanceira;

    public Conta(Long id, String descricao, Double saldoAtual, Boolean ativa, Usuario usuario, IstituicaoFinanceira instituicaoFinanceira) {
        this.id = id;
        this.descricao = descricao;
        this.saldoAtual = saldoAtual;
        this.ativa = ativa;
        this.usuario = usuario;
        this.instituicaoFinanceira = instituicaoFinanceira;
    }
}

