package fintech.backend.instituicaofinanceira.controller;

import fintech.backend.instituicaofinanceira.dto.IstituicaoFinanceiraRequestDTO;
import fintech.backend.instituicaofinanceira.dto.IstituicaoFinanceiraResponseDTO;
import fintech.backend.instituicaofinanceira.service.IstituicaoFinanceiraService;
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
@RequestMapping("/instituicoes-financeiras")
public class IstituicaoFinanceiraController {

    private final IstituicaoFinanceiraService istituicaoFinanceiraService;

    public IstituicaoFinanceiraController(IstituicaoFinanceiraService istituicaoFinanceiraService) {
        this.istituicaoFinanceiraService = istituicaoFinanceiraService;
    }

    @GetMapping
    public ResponseEntity<List<IstituicaoFinanceiraResponseDTO>> listarTodos() {
        return ResponseEntity.ok(istituicaoFinanceiraService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IstituicaoFinanceiraResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(istituicaoFinanceiraService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<IstituicaoFinanceiraResponseDTO> criar(@Valid @RequestBody IstituicaoFinanceiraRequestDTO instituicao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(istituicaoFinanceiraService.criar(instituicao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IstituicaoFinanceiraResponseDTO> atualizar(@PathVariable Long id,
            @Valid @RequestBody IstituicaoFinanceiraRequestDTO instituicao) {
        return ResponseEntity.ok(istituicaoFinanceiraService.atualizar(id, instituicao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        istituicaoFinanceiraService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

