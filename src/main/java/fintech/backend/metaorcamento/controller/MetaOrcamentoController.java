package fintech.backend.metaorcamento.controller;

import fintech.backend.metaorcamento.dto.MetaOrcamentoRequestDTO;
import fintech.backend.metaorcamento.dto.MetaOrcamentoResponseDTO;
import fintech.backend.metaorcamento.service.MetaOrcamentoService;
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
@RequestMapping("/metas-orcamento")
public class MetaOrcamentoController {

    private final MetaOrcamentoService metaOrcamentoService;

    public MetaOrcamentoController(MetaOrcamentoService metaOrcamentoService) {
        this.metaOrcamentoService = metaOrcamentoService;
    }

    @GetMapping
    public ResponseEntity<List<MetaOrcamentoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(metaOrcamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetaOrcamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(metaOrcamentoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<MetaOrcamentoResponseDTO> criar(@Valid @RequestBody MetaOrcamentoRequestDTO metaOrcamento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(metaOrcamentoService.criar(metaOrcamento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetaOrcamentoResponseDTO> atualizar(@PathVariable Long id,
            @Valid @RequestBody MetaOrcamentoRequestDTO metaOrcamento) {
        return ResponseEntity.ok(metaOrcamentoService.atualizar(id, metaOrcamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        metaOrcamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
