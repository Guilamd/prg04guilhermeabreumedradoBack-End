package fintech.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

// Padrao de resposta usado quando a API precisa retornar algum erro.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErroResponseDTO {

    private int status;

    private String erro;

    private String mensagem;

    private String caminho;

    private LocalDateTime dataHora;
}
