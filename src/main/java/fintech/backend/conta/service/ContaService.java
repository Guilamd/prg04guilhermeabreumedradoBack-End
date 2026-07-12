package fintech.backend.conta.service;

import fintech.backend.conta.dto.ContaRequestDTO;
import fintech.backend.conta.dto.ContaResponseDTO;
import fintech.backend.conta.entity.Conta;
import fintech.backend.exception.RecursoNaoEncontradoException;
import fintech.backend.exception.UsuarioNaoEncontradoException;
import fintech.backend.conta.repository.ContaRepository;
import fintech.backend.instituicaofinanceira.entity.IstituicaoFinanceira;
import fintech.backend.instituicaofinanceira.repository.IstituicaoFinanceiraRepository;
import fintech.backend.usuario.entity.Usuario;
import fintech.backend.usuario.repository.UsuarioRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    private final UsuarioRepository usuarioRepository;

    private final IstituicaoFinanceiraRepository instituicaoFinanceiraRepository;

    public ContaService(ContaRepository contaRepository, UsuarioRepository usuarioRepository, IstituicaoFinanceiraRepository instituicaoFinanceiraRepository) {
        this.contaRepository = contaRepository;
        this.usuarioRepository = usuarioRepository;
        this.instituicaoFinanceiraRepository = instituicaoFinanceiraRepository;
    }

    public List<ContaResponseDTO> listarTodos() {
        return contaRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public ContaResponseDTO buscarPorId(Long id) {
        return converterParaResponseDTO(buscarEntidadePorId(id));
    }

    @Transactional
    public ContaResponseDTO criar(ContaRequestDTO requestDTO) {
        Conta conta = new Conta();
        preencherConta(conta, requestDTO);
        return converterParaResponseDTO(contaRepository.save(conta));
    }

    @Transactional
    public ContaResponseDTO atualizar(Long id, ContaRequestDTO requestDTO) {
        Conta conta = buscarEntidadePorId(id);
        preencherConta(conta, requestDTO);
        return converterParaResponseDTO(contaRepository.save(conta));
    }

    @Transactional
    public void deletar(Long id) {
        Conta conta = buscarEntidadePorId(id);
        contaRepository.delete(conta);
    }

    private void preencherConta(Conta conta, ContaRequestDTO requestDTO) {
        Usuario usuario = usuarioRepository.findById(requestDTO.getUsuarioId()).orElseThrow(() -> new UsuarioNaoEncontradoException(requestDTO.getUsuarioId()));

        conta.setDescricao(requestDTO.getDescricao());
        conta.setSaldoAtual(requestDTO.getSaldoAtual());
        conta.setAtiva(requestDTO.getAtiva());
        conta.setUsuario(usuario);

        if (requestDTO.getInstituicaoFinanceiraId() != null) {
            IstituicaoFinanceira inst = instituicaoFinanceiraRepository.findById(requestDTO.getInstituicaoFinanceiraId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("InstituicaoFinanceira", requestDTO.getInstituicaoFinanceiraId()));
            conta.setInstituicaoFinanceira(inst);
        } else {
            conta.setInstituicaoFinanceira(null);
        }
    }

    private Conta buscarEntidadePorId(Long id) {
        return contaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Conta", id));
    }

    private ContaResponseDTO converterParaResponseDTO(Conta conta) {
        return new ContaResponseDTO(
                conta.getId(),
                conta.getDescricao(),
                conta.getSaldoAtual(),
                conta.getAtiva(),
                conta.getUsuario().getId(),
                conta.getUsuario().getNome(),
                conta.getInstituicaoFinanceira() != null ? conta.getInstituicaoFinanceira().getId() : null,
                conta.getInstituicaoFinanceira() != null ? conta.getInstituicaoFinanceira().getNomeBanco() : null);
    }
}
