package fintech.backend.exception;

// Excecao usada quando a API tenta acessar um usuario que nao existe no banco.
public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException(Long id) {
        super("Usuario com id " + id + " nao encontrado.");
    }
}
