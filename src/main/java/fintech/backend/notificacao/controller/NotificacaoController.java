package fintech.backend.notificacao.controller;

import fintech.backend.notificacao.dto.NotificacaoRequestDTO;
import fintech.backend.notificacao.dto.NotificacaoResponseDTO;
import fintech.backend.notificacao.service.NotificacaoService;
import jakarta.validation.Valid;
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
@RequestMapping("/notificacoes")
public class NotificacaoController {

    private final NotificacaoService notificacaoService;

    public NotificacaoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @GetMapping
    public ResponseEntity<List<NotificacaoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(notificacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(notificacaoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<NotificacaoResponseDTO> criar(@Valid @RequestBody NotificacaoRequestDTO notificacao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacaoService.criar(notificacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificacaoResponseDTO> atualizar(@PathVariable Long id,
            @Valid @RequestBody NotificacaoRequestDTO notificacao) {
        return ResponseEntity.ok(notificacaoService.atualizar(id, notificacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        notificacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

