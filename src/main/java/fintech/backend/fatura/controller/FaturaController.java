package fintech.backend.fatura.controller;

import fintech.backend.fatura.dto.FaturaRequestDTO;
import fintech.backend.fatura.dto.FaturaResponseDTO;
import fintech.backend.fatura.service.FaturaService;
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
@RequestMapping("/faturas")
public class FaturaController {

    private final FaturaService faturaService;

    public FaturaController(FaturaService faturaService) {
        this.faturaService = faturaService;
    }

    @GetMapping
    public ResponseEntity<Page<FaturaResponseDTO>> listar(
            @RequestParam(required = false) Long contaId,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(faturaService.listar(contaId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaturaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(faturaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<FaturaResponseDTO> criar(@Valid @RequestBody FaturaRequestDTO fatura) {
        return ResponseEntity.status(HttpStatus.CREATED).body(faturaService.criar(fatura));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FaturaResponseDTO> atualizar(@PathVariable Long id,
            @Valid @RequestBody FaturaRequestDTO fatura) {
        return ResponseEntity.ok(faturaService.atualizar(id, fatura));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        faturaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
