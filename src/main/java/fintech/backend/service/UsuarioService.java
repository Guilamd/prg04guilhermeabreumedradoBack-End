package fintech.backend.service;

import fintech.backend.entity.Usuario;
import fintech.backend.repository.UsuarioRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    // O service usa o repository para acessar os dados sem depender direto do controller.
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario criar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = buscarPorId(id);

        // Se nao encontrar o usuario, o controller vai transformar isso em 404.
        if (usuario == null) {
            return null;
        }

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setSenha(usuarioAtualizado.getSenha());

        return usuarioRepository.save(usuario);
    }

    public boolean deletar(Long id) {
        // Antes de deletar, verifica se o usuario existe para evitar erro e retornar 404.
        if (!usuarioRepository.existsById(id)) {
            return false;
        }

        usuarioRepository.deleteById(id);
        return true;
    }
}
