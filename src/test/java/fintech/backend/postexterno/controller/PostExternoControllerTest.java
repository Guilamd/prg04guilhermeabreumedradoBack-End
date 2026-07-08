package fintech.backend.postexterno.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fintech.backend.postexterno.dto.PostExternoDTO;
import fintech.backend.postexterno.service.PostExternoService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PostExternoController.class)
class PostExternoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostExternoService postExternoService;

    @Test
    void deveListarTodosPosts() throws Exception {
        List<PostExternoDTO> posts = List.of(
                new PostExternoDTO(1, 1, "Titulo 1", "Conteudo 1"),
                new PostExternoDTO(1, 2, "Titulo 2", "Conteudo 2")
        );

        when(postExternoService.buscarTodosPosts()).thenReturn(posts);

        mockMvc.perform(get("/posts-externos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Titulo 1"))
                .andExpect(jsonPath("$[1].title").value("Titulo 2"));
    }

    @Test
    void deveBuscarPostPorId() throws Exception {
        PostExternoDTO post = new PostExternoDTO(1, 1, "Titulo 1", "Conteudo 1");

        when(postExternoService.buscarPostPorId(1)).thenReturn(post);

        mockMvc.perform(get("/posts-externos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Titulo 1"));
    }

    @Test
    void deveRetornarNotFoundQuandoPostNaoExiste() throws Exception {
        when(postExternoService.buscarPostPorId(999)).thenReturn(null);

        mockMvc.perform(get("/posts-externos/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveBuscarPostsPorUsuario() throws Exception {
        List<PostExternoDTO> posts = List.of(
                new PostExternoDTO(1, 1, "Titulo 1", "Conteudo 1"),
                new PostExternoDTO(1, 2, "Titulo 2", "Conteudo 2")
        );

        when(postExternoService.buscarPostsPorUsuario(1)).thenReturn(posts);

        mockMvc.perform(get("/posts-externos/usuario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(1))
                .andExpect(jsonPath("$[1].userId").value(1))
                .andExpect(jsonPath("$[0].title").value("Titulo 1"));
    }
}
