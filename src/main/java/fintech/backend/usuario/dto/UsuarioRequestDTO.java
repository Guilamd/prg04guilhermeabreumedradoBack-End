package fintech.backend.usuario.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// DTO usado para receber os dados enviados pelo cliente nas requisicoes.
@Data
@NoArgsConstructor
public class UsuarioRequestDTO {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    private String nome;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O email deve ter um formato válido.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, max = 50, message = "A senha deve ter entre 6 e 50 caracteres.")
    private String senha;
}
