package fintech.backend.service;

import fintech.backend.dto.TransacaoRequestDTO;
import fintech.backend.dto.TransacaoResponseDTO;
import fintech.backend.entity.Categoria;
import fintech.backend.entity.Conta;
import fintech.backend.entity.Transacao;
import fintech.backend.exception.RecursoNaoEncontradoException;
import fintech.backend.repository.CategoriaRepository;
import fintech.backend.repository.ContaRepository;
import fintech.backend.repository.TransacaoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    private final ContaRepository contaRepository;

    private final CategoriaRepository categoriaRepository;

    public TransacaoService(TransacaoRepository transacaoRepository, ContaRepository contaRepository,
            CategoriaRepository categoriaRepository) {
        this.transacaoRepository = transacaoRepository;
        this.contaRepository = contaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<TransacaoResponseDTO> listarTodos() {
        return transacaoRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public TransacaoResponseDTO buscarPorId(Long id) {
        return converterParaResponseDTO(buscarEntidadePorId(id));
    }

    @Transactional
    public TransacaoResponseDTO criar(TransacaoRequestDTO requestDTO) {
        Transacao transacao = new Transacao();
        preencherTransacao(transacao, requestDTO);
        return converterParaResponseDTO(transacaoRepository.save(transacao));
    }

    @Transactional
    public TransacaoResponseDTO atualizar(Long id, TransacaoRequestDTO requestDTO) {
        Transacao transacao = buscarEntidadePorId(id);
        preencherTransacao(transacao, requestDTO);
        return converterParaResponseDTO(transacaoRepository.save(transacao));
    }

    @Transactional
    public void deletar(Long id) {
        Transacao transacao = buscarEntidadePorId(id);
        transacaoRepository.delete(transacao);
    }

    private void preencherTransacao(Transacao transacao, TransacaoRequestDTO requestDTO) {
        Conta conta = contaRepository.findById(requestDTO.getContaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Conta", requestDTO.getContaId()));
        Categoria categoria = categoriaRepository.findById(requestDTO.getCategoriaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria", requestDTO.getCategoriaId()));

        transacao.setTitulo(requestDTO.getTitulo());
        transacao.setValor(requestDTO.getValor());
        transacao.setDataHora(requestDTO.getDataHora());
        transacao.setOrigem(requestDTO.getOrigem());
        transacao.setTipoMovimentacao(requestDTO.getTipoMovimentacao());
        transacao.setTipoPagamento(requestDTO.getTipoPagamento());
        transacao.setStatus(requestDTO.getStatus());
        transacao.setConta(conta);
        transacao.setCategoria(categoria);
    }

    private Transacao buscarEntidadePorId(Long id) {
        return transacaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Transacao", id));
    }

    private TransacaoResponseDTO converterParaResponseDTO(Transacao transacao) {
        return new TransacaoResponseDTO(
                transacao.getId(),
                transacao.getTitulo(),
                transacao.getValor(),
                transacao.getDataHora(),
                transacao.getOrigem(),
                transacao.getTipoMovimentacao(),
                transacao.getTipoPagamento(),
                transacao.getStatus(),
                transacao.getConta().getId(),
                transacao.getConta().getDescricao(),
                transacao.getCategoria().getId(),
                transacao.getCategoria().getNome());
    }
}
