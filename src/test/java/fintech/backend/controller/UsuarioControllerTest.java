package fintech.backend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fintech.backend.entity.Usuario;
import fintech.backend.service.UsuarioService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private UsuarioService usuarioService;

        @Test
        void deveListarUsuarios() throws Exception {
                when(usuarioService.listarTodos())
                                .thenReturn(List.of(
                                                new Usuario(1L, "Usuario Teste", "usuario.teste@email.com", "123456")));

                mockMvc.perform(get("/usuarios"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].nome").value("Usuario Teste"));
        }

        @Test
        void deveCriarUsuario() throws Exception {
                when(usuarioService.criar(any(Usuario.class)))
                                .thenReturn(new Usuario(1L, "Usuario Teste", "usuario.teste@email.com", "123456"));

                mockMvc.perform(post("/usuarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                                {
                                                  "nome": "Usuario Teste",
                                                  "email": "usuario.teste@email.com",
                                                  "senha": "123456"
                                                }
                                                """))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.nome").value("Usuario Teste"));
        }

        @Test
        void deveBuscarUsuarioPorId() throws Exception {
                when(usuarioService.buscarPorId(1L))
                                .thenReturn(new Usuario(1L, "Usuario Teste", "usuario.teste@email.com", "123456"));

                mockMvc.perform(get("/usuarios/1"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.email").value("usuario.teste@email.com"));
        }

        @Test
        void deveRetornarNotFoundQuandoUsuarioNaoExiste() throws Exception {
                when(usuarioService.buscarPorId(99L)).thenReturn(null);

                mockMvc.perform(get("/usuarios/99"))
                                .andExpect(status().isNotFound());
        }

        @Test
        void deveAtualizarUsuario() throws Exception {
                when(usuarioService.atualizar(any(Long.class), any(Usuario.class)))
                                .thenReturn(new Usuario(1L, "Usuario Atualizado", "usuario.atualizado@email.com",
                                                "654321"));

                mockMvc.perform(put("/usuarios/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                                {
                                                  "nome": "Usuario Atualizado",
                                                  "email": "usuario.atualizado@email.com",
                                                  "senha": "654321"
                                                }
                                                """))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.nome").value("Usuario Atualizado"))
                                .andExpect(jsonPath("$.email").value("usuario.atualizado@email.com"));
        }

        @Test
        void deveDeletarUsuario() throws Exception {
                when(usuarioService.deletar(1L)).thenReturn(true);

                mockMvc.perform(delete("/usuarios/1"))
                                .andExpect(status().isNoContent());
        }
}
