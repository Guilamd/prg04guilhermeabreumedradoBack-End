package fintech.backend.instituicaofinanceira.service;

import fintech.backend.exception.RecursoNaoEncontradoException;
import fintech.backend.instituicaofinanceira.dto.IstituicaoFinanceiraRequestDTO;
import fintech.backend.instituicaofinanceira.dto.IstituicaoFinanceiraResponseDTO;
import fintech.backend.instituicaofinanceira.entity.IstituicaoFinanceira;
import fintech.backend.instituicaofinanceira.repository.IstituicaoFinanceiraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class IstituicaoFinanceiraService {

    private final IstituicaoFinanceiraRepository istituicaoFinanceiraRepository;

    public IstituicaoFinanceiraService(IstituicaoFinanceiraRepository istituicaoFinanceiraRepository) {
        this.istituicaoFinanceiraRepository = istituicaoFinanceiraRepository;
    }

    public List<IstituicaoFinanceiraResponseDTO> listarTodos() {
        return istituicaoFinanceiraRepository.findAll().stream().map(this::converterParaResponseDTO).toList();
    }

    public IstituicaoFinanceiraResponseDTO buscarPorId(Long id) {
        return converterParaResponseDTO(buscarEntidadePorId(id));
    }

    @Transactional
    public IstituicaoFinanceiraResponseDTO criar(IstituicaoFinanceiraRequestDTO requestDTO) {
        IstituicaoFinanceira instituicao = new IstituicaoFinanceira();
        preencherInstituicao(instituicao, requestDTO);
        return converterParaResponseDTO(istituicaoFinanceiraRepository.save(instituicao));
    }

    @Transactional
    public IstituicaoFinanceiraResponseDTO atualizar(Long id, IstituicaoFinanceiraRequestDTO requestDTO) {
        IstituicaoFinanceira instituicao = buscarEntidadePorId(id);
        preencherInstituicao(instituicao, requestDTO);
        return converterParaResponseDTO(istituicaoFinanceiraRepository.save(instituicao));
    }

    @Transactional
    public void deletar(Long id) {
        IstituicaoFinanceira instituicao = buscarEntidadePorId(id);
        istituicaoFinanceiraRepository.delete(instituicao);
    }

    private void preencherInstituicao(IstituicaoFinanceira instituicao, IstituicaoFinanceiraRequestDTO requestDTO) {
        instituicao.setNomeBanco(requestDTO.getNomeBanco());
        instituicao.setCodigoCompensacao(requestDTO.getCodigoCompensacao());
    }

    private IstituicaoFinanceira buscarEntidadePorId(Long id) {
        return istituicaoFinanceiraRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("IstituicaoFinanceira", id));
    }

    private IstituicaoFinanceiraResponseDTO converterParaResponseDTO(IstituicaoFinanceira instituicao) {
        return new IstituicaoFinanceiraResponseDTO(
                instituicao.getId(),
                instituicao.getNomeBanco(),
                instituicao.getCodigoCompensacao()
        );
    }
}

