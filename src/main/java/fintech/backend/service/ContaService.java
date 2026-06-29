package fintech.backend.service;

import fintech.backend.dto.ContaRequestDTO;
import fintech.backend.dto.ContaResponseDTO;
import fintech.backend.entity.Conta;
import fintech.backend.entity.Usuario;
import fintech.backend.exception.RecursoNaoEncontradoException;
import fintech.backend.exception.UsuarioNaoEncontradoException;
import fintech.backend.repository.ContaRepository;
import fintech.backend.repository.UsuarioRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    private final UsuarioRepository usuarioRepository;

    public ContaService(ContaRepository contaRepository, UsuarioRepository usuarioRepository) {
        this.contaRepository = contaRepository;
        this.usuarioRepository = usuarioRepository;
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
        Usuario usuario = usuarioRepository.findById(requestDTO.getUsuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(requestDTO.getUsuarioId()));

        conta.setDescricao(requestDTO.getDescricao());
        conta.setSaldoAtual(requestDTO.getSaldoAtual());
        conta.setAtiva(requestDTO.getAtiva());
        conta.setUsuario(usuario);
    }

    private Conta buscarEntidadePorId(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Conta", id));
    }

    private ContaResponseDTO converterParaResponseDTO(Conta conta) {
        return new ContaResponseDTO(
                conta.getId(),
                conta.getDescricao(),
                conta.getSaldoAtual(),
                conta.getAtiva(),
                conta.getUsuario().getId(),
                conta.getUsuario().getNome());
    }
}
