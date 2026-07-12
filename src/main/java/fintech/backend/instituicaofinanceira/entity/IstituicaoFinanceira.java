package fintech.backend.instituicaofinanceira.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "instituicoes_financeiras")
@Data
@NoArgsConstructor
public class IstituicaoFinanceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeBanco;

    private String codigoCompensacao;

    public IstituicaoFinanceira(Long id, String nomeBanco, String codigoCompensacao) {
        this.id = id;
        this.nomeBanco = nomeBanco;
        this.codigoCompensacao = codigoCompensacao;
    }
}
