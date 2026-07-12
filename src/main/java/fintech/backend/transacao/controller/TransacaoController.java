package fintech.backend.transacao.controller;

import fintech.backend.transacao.dto.ResumoDashboardDTO;
import fintech.backend.transacao.dto.TransacaoRequestDTO;
import fintech.backend.transacao.dto.TransacaoResponseDTO;
import fintech.backend.transacao.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public ResponseEntity<Page<TransacaoResponseDTO>> listar(
            @RequestParam(required = false) Long contaId,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(transacaoService.listar(contaId, pageable));
    }

    @GetMapping("/resumo")
    public ResponseEntity<ResumoDashboardDTO> obterResumo(
            @RequestParam Long contaId,
            @RequestParam String mesAno) {
        return ResponseEntity.ok(transacaoService.obterResumo(contaId, mesAno));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(transacaoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TransacaoResponseDTO> criar(@Valid @RequestBody TransacaoRequestDTO transacao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transacaoService.criar(transacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransacaoResponseDTO> atualizar(@PathVariable Long id,
            @Valid @RequestBody TransacaoRequestDTO transacao) {
        return ResponseEntity.ok(transacaoService.atualizar(id, transacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        transacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
