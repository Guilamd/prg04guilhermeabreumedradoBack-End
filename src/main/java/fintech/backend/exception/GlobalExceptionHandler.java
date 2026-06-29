package fintech.backend.exception;

import fintech.backend.dto.ErroResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Centraliza o tratamento de erros para evitar repeticao de try/catch nos controllers.
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Quando um usuario nao e encontrado, a resposta volta padronizada com status 404.
    @ExceptionHandler({UsuarioNaoEncontradoException.class, RecursoNaoEncontradoException.class})
    public ResponseEntity<ErroResponseDTO> tratarUsuarioNaoEncontrado(
            RuntimeException exception,
            HttpServletRequest request) {

        ErroResponseDTO erro = criarErro(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // Captura erros de validacao dos campos dos DTOs anotados com Bean Validation.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarErrosDeValidacao(
            MethodArgumentNotValidException exception,
            HttpServletRequest request) {

        // Monta um mapa com o nome do campo e a mensagem de erro correspondente.
        Map<String, String> errosCampos = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(erro ->
                errosCampos.put(erro.getField(), erro.getDefaultMessage())
        );

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", HttpStatus.BAD_REQUEST.value());
        resposta.put("erro", "Erro de validação");
        resposta.put("mensagem", "Um ou mais campos possuem valores inválidos.");
        resposta.put("campos", errosCampos);
        resposta.put("caminho", request.getRequestURI());
        resposta.put("dataHora", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    // Tratamento geral para erros inesperados que nao possuem uma regra especifica.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponseDTO> tratarErroInterno(Exception exception, HttpServletRequest request) {
        ErroResponseDTO erro = criarErro(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro interno no servidor.",
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

    // Monta o corpo da resposta de erro sempre no mesmo formato.
    private ErroResponseDTO criarErro(HttpStatus status, String mensagem, String caminho) {
        return new ErroResponseDTO(
                status.value(),
                status.getReasonPhrase(),
                mensagem,
                caminho,
                LocalDateTime.now());
    }
}
