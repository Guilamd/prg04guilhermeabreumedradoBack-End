package fintech.backend.usuario.controller;

import fintech.backend.usuario.dto.UsuarioRequestDTO;
import fintech.backend.usuario.dto.UsuarioResponseDTO;
import fintech.backend.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<UsuarioResponseDTO>> listarTodosPaginado(Pageable pageable) {
        // Retorna uma pagina de usuarios. Parametros: page (0-indexed), size, sort
        return ResponseEntity.ok(usuarioService.listarTodosPaginado(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@Valid @RequestBody UsuarioRequestDTO usuario) {
        UsuarioResponseDTO usuarioCriado = usuarioService.criar(usuario);
        // 201 indica que um novo recurso foi criado com sucesso.
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO usuario) {
        return ResponseEntity.ok(usuarioService.atualizar(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        // 204 indica sucesso sem precisar devolver conteudo no corpo da resposta.
        return ResponseEntity.noContent().build();
    }
}
