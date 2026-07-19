package fintech.backend.metaorcamento.service;

import fintech.backend.categoria.entity.Categoria;
import fintech.backend.categoria.repository.CategoriaRepository;
import fintech.backend.exception.RecursoNaoEncontradoException;
import fintech.backend.metaorcamento.dto.MetaOrcamentoRequestDTO;
import fintech.backend.metaorcamento.dto.MetaOrcamentoResponseDTO;
import fintech.backend.metaorcamento.entity.MetaOrcamento;
import fintech.backend.metaorcamento.repository.MetaOrcamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MetaOrcamentoService {

    private final MetaOrcamentoRepository metaOrcamentoRepository;

    private final CategoriaRepository categoriaRepository;

    public MetaOrcamentoService(MetaOrcamentoRepository metaOrcamentoRepository,
            CategoriaRepository categoriaRepository) {
        this.metaOrcamentoRepository = metaOrcamentoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<MetaOrcamentoResponseDTO> listarTodos() {
        return metaOrcamentoRepository.findAll().stream().map(this::converterParaResponseDTO).toList();
    }

    public MetaOrcamentoResponseDTO buscarPorId(Long id) {
        return converterParaResponseDTO(buscarEntidadePorId(id));
    }

    @Transactional
    public MetaOrcamentoResponseDTO criar(MetaOrcamentoRequestDTO requestDTO) {
        MetaOrcamento metaOrcamento = new MetaOrcamento();
        preencherMetaOrcamento(metaOrcamento, requestDTO);
        return converterParaResponseDTO(metaOrcamentoRepository.save(metaOrcamento));
    }

    @Transactional
    public MetaOrcamentoResponseDTO atualizar(Long id, MetaOrcamentoRequestDTO requestDTO) {
        MetaOrcamento metaOrcamento = buscarEntidadePorId(id);
        preencherMetaOrcamento(metaOrcamento, requestDTO);
        return converterParaResponseDTO(metaOrcamentoRepository.save(metaOrcamento));
    }

    @Transactional
    public void deletar(Long id) {
        MetaOrcamento metaOrcamento = buscarEntidadePorId(id);
        metaOrcamentoRepository.delete(metaOrcamento);
    }

    private void preencherMetaOrcamento(MetaOrcamento metaOrcamento, MetaOrcamentoRequestDTO requestDTO) {
        Categoria categoria = categoriaRepository.findById(requestDTO.getCategoriaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria", requestDTO.getCategoriaId()));

        metaOrcamento.setValorLimite(requestDTO.getValorLimite());
        metaOrcamento.setMesAnoReferencia(requestDTO.getMesAnoReferencia());
        metaOrcamento.setCategoria(categoria);
    }

    private MetaOrcamento buscarEntidadePorId(Long id) {
        return metaOrcamentoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("MetaOrcamento", id));
    }

    private MetaOrcamentoResponseDTO converterParaResponseDTO(MetaOrcamento metaOrcamento) {
        return new MetaOrcamentoResponseDTO(
                metaOrcamento.getId(),
                metaOrcamento.getValorLimite(),
                metaOrcamento.getMesAnoReferencia(),
                metaOrcamento.getCategoria().getId(),
                metaOrcamento.getCategoria().getNome());
    }
}
