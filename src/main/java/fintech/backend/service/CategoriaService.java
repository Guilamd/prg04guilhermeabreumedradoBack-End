package fintech.backend.service;

import fintech.backend.dto.CategoriaRequestDTO;
import fintech.backend.dto.CategoriaResponseDTO;
import fintech.backend.entity.Categoria;
import fintech.backend.entity.Usuario;
import fintech.backend.exception.RecursoNaoEncontradoException;
import fintech.backend.exception.UsuarioNaoEncontradoException;
import fintech.backend.repository.CategoriaRepository;
import fintech.backend.repository.UsuarioRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    private final UsuarioRepository usuarioRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, UsuarioRepository usuarioRepository) {
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<CategoriaResponseDTO> listarTodos() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public CategoriaResponseDTO buscarPorId(Long id) {
        return converterParaResponseDTO(buscarEntidadePorId(id));
    }

    @Transactional
    public CategoriaResponseDTO criar(CategoriaRequestDTO requestDTO) {
        Categoria categoria = new Categoria();
        preencherCategoria(categoria, requestDTO);
        return converterParaResponseDTO(categoriaRepository.save(categoria));
    }

    @Transactional
    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO requestDTO) {
        Categoria categoria = buscarEntidadePorId(id);
        preencherCategoria(categoria, requestDTO);
        return converterParaResponseDTO(categoriaRepository.save(categoria));
    }

    @Transactional
    public void deletar(Long id) {
        Categoria categoria = buscarEntidadePorId(id);
        categoriaRepository.delete(categoria);
    }

    private void preencherCategoria(Categoria categoria, CategoriaRequestDTO requestDTO) {
        Usuario usuario = usuarioRepository.findById(requestDTO.getUsuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(requestDTO.getUsuarioId()));

        categoria.setNome(requestDTO.getNome());
        categoria.setCorHexadecimal(requestDTO.getCorHexadecimal());
        categoria.setAtiva(requestDTO.getAtiva());
        categoria.setUsuario(usuario);
    }

    private Categoria buscarEntidadePorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria", id));
    }

    private CategoriaResponseDTO converterParaResponseDTO(Categoria categoria) {
        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNome(),
                categoria.getCorHexadecimal(),
                categoria.getAtiva(),
                categoria.getUsuario().getId(),
                categoria.getUsuario().getNome());
    }
}
