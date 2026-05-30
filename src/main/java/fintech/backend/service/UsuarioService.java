package fintech.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fintech.backend.dto.UsuarioRequestDTO;
import fintech.backend.dto.UsuarioResponseDTO;
import fintech.backend.entity.Usuario;
import fintech.backend.exception.UsuarioNaoEncontradoException;
import fintech.backend.repository.UsuarioRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    // O service usa o repository para acessar os dados sem depender direto do
    // controller.
    private final UsuarioRepository usuarioRepository;

    // O ObjectMapper faz a conversao entre DTO e entidade sem precisar copiar campo por campo.
    private final ObjectMapper objectMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, ObjectMapper objectMapper) {
        this.usuarioRepository = usuarioRepository;
        this.objectMapper = objectMapper;
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = buscarEntidadePorId(id);
        return converterParaResponseDTO(usuario);
    }

    // Garante que a criacao do usuario aconteca dentro de uma transacao.
    @Transactional
    public UsuarioResponseDTO criar(UsuarioRequestDTO usuarioRequestDTO) {
        // Converte o DTO recebido na requisicao para a entidade que sera salva no banco.
        Usuario usuario = objectMapper.convertValue(usuarioRequestDTO, Usuario.class);
        Usuario usuarioCriado = usuarioRepository.save(usuario);

        return converterParaResponseDTO(usuarioCriado);
    }

    // Garante que a atualizacao do usuario aconteca dentro de uma transacao.
    @Transactional
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO usuarioAtualizado) {
        Usuario usuario = buscarEntidadePorId(id);

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setSenha(usuarioAtualizado.getSenha());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return converterParaResponseDTO(usuarioSalvo);
    }

    // Garante que a exclusao do usuario aconteca dentro de uma transacao.
    @Transactional
    public void deletar(Long id) {
        Usuario usuario = buscarEntidadePorId(id);
        usuarioRepository.delete(usuario);
    }

    private Usuario buscarEntidadePorId(Long id) {
        // Se nao encontrar, dispara uma excecao para o handler centralizado tratar.
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    private UsuarioResponseDTO converterParaResponseDTO(Usuario usuario) {
        // Converte a entidade para o DTO de resposta, evitando retornar a senha.
        return objectMapper.convertValue(usuario, UsuarioResponseDTO.class);
    }
}

