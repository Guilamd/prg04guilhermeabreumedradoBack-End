package fintech.backend.postexterno.controller;

import fintech.backend.postexterno.dto.PostExternoDTO;
import fintech.backend.postexterno.service.PostExternoService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Controller que expoe endpoints para consumir a API externa JSONPlaceholder.
@RestController
@RequestMapping("/posts-externos")
public class PostExternoController {

    private final PostExternoService postExternoService;

    public PostExternoController(PostExternoService postExternoService) {
        this.postExternoService = postExternoService;
    }

    @GetMapping
    public ResponseEntity<List<PostExternoDTO>> listarTodosPosts() {
        // Consome a API externa e retorna todos os posts disponveis.
        return ResponseEntity.ok(postExternoService.buscarTodosPosts());
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<PostExternoDTO>> buscarPostsPorUsuario(@PathVariable Integer userId) {
        // Consome a API externa e busca todos os posts de um usuario especifico.
        // NOTA: Esta rota DEVE estar antes de /{id} para evitar conflito de rotas
        return ResponseEntity.ok(postExternoService.buscarPostsPorUsuario(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostExternoDTO> buscarPostPorId(@PathVariable Integer id) {
        // Consome a API externa e busca um post especifico pelo id.
        PostExternoDTO post = postExternoService.buscarPostPorId(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(post);
    }
}
