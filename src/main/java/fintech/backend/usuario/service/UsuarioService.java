package fintech.backend.usuario.service;

import fintech.backend.usuario.dto.UsuarioRequestDTO;
import fintech.backend.usuario.dto.UsuarioPerfilRequestDTO;
import fintech.backend.usuario.dto.UsuarioResponseDTO;
import fintech.backend.usuario.entity.Usuario;
import fintech.backend.exception.UsuarioNaoEncontradoException;
import fintech.backend.usuario.repository.UsuarioRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public Page<UsuarioResponseDTO> listarTodosPaginado(Pageable pageable) {
        // Retorna uma pagina de usuarios de acordo com os parametros fornecidos.
        return usuarioRepository.findAll(pageable)
                .map(this::converterParaResponseDTO);
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = buscarEntidadePorId(id);
        return converterParaResponseDTO(usuario);
    }

    public UsuarioResponseDTO login(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("E-mail ou senha inválidos"));

        // Comparação simples (já que as senhas não estão sendo criptografadas com BCrypt no momento)
        if (!usuario.getSenhaHash().equals(senha)) {
            throw new RuntimeException("E-mail ou senha inválidos");
        }

        return converterParaResponseDTO(usuario);
    }

    // Garante que a criacao do usuario aconteca dentro de uma transacao.
    @Transactional
    public UsuarioResponseDTO criar(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setSenhaHash(usuarioRequestDTO.getSenha());

        Usuario usuarioCriado = usuarioRepository.save(usuario);

        return converterParaResponseDTO(usuarioCriado);
    }

    @Transactional
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO usuarioAtualizado) {
        Usuario usuario = buscarEntidadePorId(id);

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setSenhaHash(usuarioAtualizado.getSenha());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return converterParaResponseDTO(usuarioSalvo);
    }

    @Transactional
    public UsuarioResponseDTO atualizarPerfil(Long id, UsuarioPerfilRequestDTO perfilAtualizado) {
        Usuario usuario = buscarEntidadePorId(id);

        usuario.setNome(perfilAtualizado.getNome());
        usuario.setEmail(perfilAtualizado.getEmail());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return converterParaResponseDTO(usuarioSalvo);
    }

    @Transactional
    public void deletar(Long id) {
        Usuario usuario = buscarEntidadePorId(id);
        usuarioRepository.delete(usuario);
    }

    private Usuario buscarEntidadePorId(Long id) {
        // Se nao encontrar, dispara uma excecao para o handler centralizado tratar.
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    private UsuarioResponseDTO converterParaResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDataCriacao());
    }
}
