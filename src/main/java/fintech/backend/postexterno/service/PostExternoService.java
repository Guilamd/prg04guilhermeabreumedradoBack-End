package fintech.backend.postexterno.service;

import fintech.backend.postexterno.dto.PostExternoDTO;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

// Service para consumir dados da API externa JSONPlaceholder utilizando WebClient.
@Service
public class PostExternoService {

    private final WebClient webClient;

    public PostExternoService(WebClient webClient) {
        this.webClient = webClient;
    }

    // Busca todos os posts da API externa.
    public List<PostExternoDTO> buscarTodosPosts() {
        PostExternoDTO[] posts = webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToMono(PostExternoDTO[].class)
                .block();

        return posts != null ? Arrays.asList(posts) : List.of();
    }

    // Busca um post especifico 
    public PostExternoDTO buscarPostPorId(Integer id) {
        return webClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToMono(PostExternoDTO.class)
                .block();
    }

    // Busca todos os posts de um usuario especifico.
    public List<PostExternoDTO> buscarPostsPorUsuario(Integer userId) {
        PostExternoDTO[] posts = webClient.get()
                .uri("/posts?userId={userId}", userId)
                .retrieve()
                .bodyToMono(PostExternoDTO[].class)
                .block();

        return posts != null ? Arrays.asList(posts) : List.of();
    }
}
