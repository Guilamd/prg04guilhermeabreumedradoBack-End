package fintech.backend.controller;

import fintech.backend.dto.UsuarioRequestDTO;
import fintech.backend.dto.UsuarioResponseDTO;
import fintech.backend.service.UsuarioService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    // O controller recebe a requisicao HTTP e delega a regra de negocio para o
    // service.
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        // Retorna a lista de todos os usuarios cadastrados no banco.
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        // Busca um usuario especifico no banco pelo seu id.
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@RequestBody UsuarioRequestDTO usuario) {
        UsuarioResponseDTO usuarioCriado = usuarioService.criar(usuario);
        // 201 indica que um novo recurso foi criado com sucesso.
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuario) {
        // Atualiza os dados do usuario no banco com as novas informacoes.
        return ResponseEntity.ok(usuarioService.atualizar(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        // Deleta o usuario do banco usando o id fornecido.
        usuarioService.deletar(id);
        // 204 indica sucesso sem precisar devolver conteudo no corpo da resposta.
        return ResponseEntity.noContent().build();
    }
}
