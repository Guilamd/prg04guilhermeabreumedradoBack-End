package fintech.backend.notificacao.service;

import fintech.backend.exception.RecursoNaoEncontradoException;
import fintech.backend.exception.UsuarioNaoEncontradoException;
import fintech.backend.notificacao.dto.NotificacaoRequestDTO;
import fintech.backend.notificacao.dto.NotificacaoResponseDTO;
import fintech.backend.notificacao.entity.Notificacao;
import fintech.backend.notificacao.repository.NotificacaoRepository;
import fintech.backend.metaorcamento.entity.MetaOrcamento;
import fintech.backend.metaorcamento.repository.MetaOrcamentoRepository;
import fintech.backend.usuario.entity.Usuario;
import fintech.backend.usuario.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;

    private final UsuarioRepository usuarioRepository;

    private final MetaOrcamentoRepository metaOrcamentoRepository;

    public NotificacaoService(NotificacaoRepository notificacaoRepository, UsuarioRepository usuarioRepository,
            MetaOrcamentoRepository metaOrcamentoRepository) {
        this.notificacaoRepository = notificacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.metaOrcamentoRepository = metaOrcamentoRepository;
    }

    public List<NotificacaoResponseDTO> listarTodos() {
        return notificacaoRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public NotificacaoResponseDTO buscarPorId(Long id) {
        return converterParaResponseDTO(buscarEntidadePorId(id));
    }

    @Transactional
    public NotificacaoResponseDTO criar(NotificacaoRequestDTO requestDTO) {
        Notificacao notificacao = new Notificacao();
        preencherNotificacao(notificacao, requestDTO);
        return converterParaResponseDTO(notificacaoRepository.save(notificacao));
    }

    @Transactional
    public NotificacaoResponseDTO atualizar(Long id, NotificacaoRequestDTO requestDTO) {
        Notificacao notificacao = buscarEntidadePorId(id);
        preencherNotificacao(notificacao, requestDTO);
        return converterParaResponseDTO(notificacaoRepository.save(notificacao));
    }

    @Transactional
    public void deletar(Long id) {
        Notificacao notificacao = buscarEntidadePorId(id);
        notificacaoRepository.delete(notificacao);
    }

    private void preencherNotificacao(Notificacao notificacao, NotificacaoRequestDTO requestDTO) {
        Usuario usuario = usuarioRepository.findById(requestDTO.getUsuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(requestDTO.getUsuarioId()));

        notificacao.setMensagem(requestDTO.getMensagem());
        notificacao.setDataEnvio(requestDTO.getDataEnvio());
        notificacao.setLido(requestDTO.getLido());
        notificacao.setUsuario(usuario);

        if (requestDTO.getMetaOrcamentoId() != null) {
            MetaOrcamento metaOrcamento = metaOrcamentoRepository.findById(requestDTO.getMetaOrcamentoId()).orElseThrow(
                    () -> new RecursoNaoEncontradoException("MetaOrcamento", requestDTO.getMetaOrcamentoId()));
            notificacao.setMetaOrcamento(metaOrcamento);
        } else {
            notificacao.setMetaOrcamento(null);
        }
    }

    private Notificacao buscarEntidadePorId(Long id) {
        return notificacaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Notificacao", id));
    }

    private NotificacaoResponseDTO converterParaResponseDTO(Notificacao notificacao) {
        return new NotificacaoResponseDTO(
                notificacao.getId(),
                notificacao.getMensagem(),
                notificacao.getDataEnvio(),
                notificacao.getLido(),
                notificacao.getUsuario().getId(),
                notificacao.getUsuario().getNome(),
                notificacao.getMetaOrcamento() != null ? notificacao.getMetaOrcamento().getId() : null,
                notificacao.getMetaOrcamento() != null ? notificacao.getMetaOrcamento().getMesAnoReferencia() : null);
    }
}
