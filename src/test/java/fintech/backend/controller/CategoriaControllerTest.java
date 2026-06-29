package fintech.backend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fintech.backend.dto.CategoriaRequestDTO;
import fintech.backend.dto.CategoriaResponseDTO;
import fintech.backend.exception.RecursoNaoEncontradoException;
import fintech.backend.service.CategoriaService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CategoriaController.class)
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;

    @Test
    void deveListarCategorias() throws Exception {
        when(categoriaService.listarTodos())
                .thenReturn(List.of(new CategoriaResponseDTO(1L, "Alimentacao", "#22C55E", true, 1L, "Usuario Teste")));

        mockMvc.perform(get("/categorias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Alimentacao"))
                .andExpect(jsonPath("$[0].usuarioId").value(1));
    }

    @Test
    void deveCriarCategoria() throws Exception {
        when(categoriaService.criar(any(CategoriaRequestDTO.class)))
                .thenReturn(new CategoriaResponseDTO(1L, "Alimentacao", "#22C55E", true, 1L, "Usuario Teste"));

        mockMvc.perform(post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nome": "Alimentacao",
                                  "corHexadecimal": "#22C55E",
                                  "ativa": true,
                                  "usuarioId": 1
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Alimentacao"))
                .andExpect(jsonPath("$.usuarioNome").value("Usuario Teste"));
    }

    @Test
    void deveBuscarCategoriaPorId() throws Exception {
        when(categoriaService.buscarPorId(1L))
                .thenReturn(new CategoriaResponseDTO(1L, "Alimentacao", "#22C55E", true, 1L, "Usuario Teste"));

        mockMvc.perform(get("/categorias/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.corHexadecimal").value("#22C55E"));
    }

    @Test
    void deveRetornarNotFoundQuandoCategoriaNaoExiste() throws Exception {
        when(categoriaService.buscarPorId(99L)).thenThrow(new RecursoNaoEncontradoException("Categoria", 99L));

        mockMvc.perform(get("/categorias/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.mensagem").value("Categoria com id 99 nao encontrado."));
    }

    @Test
    void deveAtualizarCategoria() throws Exception {
        when(categoriaService.atualizar(any(Long.class), any(CategoriaRequestDTO.class)))
                .thenReturn(new CategoriaResponseDTO(1L, "Mercado", "#16A34A", true, 1L, "Usuario Teste"));

        mockMvc.perform(put("/categorias/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nome": "Mercado",
                                  "corHexadecimal": "#16A34A",
                                  "ativa": true,
                                  "usuarioId": 1
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Mercado"))
                .andExpect(jsonPath("$.corHexadecimal").value("#16A34A"));
    }

    @Test
    void deveDeletarCategoria() throws Exception {
        mockMvc.perform(delete("/categorias/1"))
                .andExpect(status().isNoContent());
    }
}
