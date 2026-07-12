package fintech.backend.fatura.service;

import fintech.backend.conta.entity.Conta;
import fintech.backend.conta.repository.ContaRepository;
import fintech.backend.exception.RecursoNaoEncontradoException;
import fintech.backend.fatura.dto.FaturaRequestDTO;
import fintech.backend.fatura.dto.FaturaResponseDTO;
import fintech.backend.fatura.entity.Fatura;
import fintech.backend.fatura.repository.FaturaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class FaturaService {

    private final FaturaRepository faturaRepository;

    private final ContaRepository contaRepository;

    public FaturaService(FaturaRepository faturaRepository, ContaRepository contaRepository) {
        this.faturaRepository = faturaRepository;
        this.contaRepository = contaRepository;
    }

    public Page<FaturaResponseDTO> listar(Long contaId, Pageable pageable) {
        if (contaId != null) {
            return faturaRepository.findByContaId(contaId, pageable).map(this::converterParaResponseDTO);
        }
        return faturaRepository.findAll(pageable).map(this::converterParaResponseDTO);
    }

    public FaturaResponseDTO buscarPorId(Long id) {
        return converterParaResponseDTO(buscarEntidadePorId(id));
    }

    @Transactional
    public FaturaResponseDTO criar(FaturaRequestDTO requestDTO) {
        Fatura fatura = new Fatura();
        preencherFatura(fatura, requestDTO);
        return converterParaResponseDTO(faturaRepository.save(fatura));
    }

    @Transactional
    public FaturaResponseDTO atualizar(Long id, FaturaRequestDTO requestDTO) {
        Fatura fatura = buscarEntidadePorId(id);
        preencherFatura(fatura, requestDTO);
        return converterParaResponseDTO(faturaRepository.save(fatura));
    }

    @Transactional
    public void deletar(Long id) {
        Fatura fatura = buscarEntidadePorId(id);
        faturaRepository.delete(fatura);
    }

    private void preencherFatura(Fatura fatura, FaturaRequestDTO requestDTO) {
        Conta conta = contaRepository.findById(requestDTO.getContaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Conta", requestDTO.getContaId()));

        fatura.setMesAnoReferencia(requestDTO.getMesAnoReferencia());
        fatura.setValorTotal(requestDTO.getValorTotal());
        fatura.setDataVencimento(requestDTO.getDataVencimento());
        fatura.setEstaPaga(requestDTO.getEstaPaga());
        fatura.setConta(conta);
    }

    private Fatura buscarEntidadePorId(Long id) {
        return faturaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Fatura", id));
    }

    private FaturaResponseDTO converterParaResponseDTO(Fatura fatura) {
        return new FaturaResponseDTO(
                fatura.getId(),
                fatura.getMesAnoReferencia(),
                fatura.getValorTotal(),
                fatura.getDataVencimento(),
                fatura.getEstaPaga(),
                fatura.getConta().getId(),
                fatura.getConta().getDescricao());
    }
}
