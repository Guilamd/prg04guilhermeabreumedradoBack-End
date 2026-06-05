package fintech.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

// Configuracao centralizada do WebClient para consumo de APIs externas.
@Configuration
public class WebClientConfig {

    // Bean do WebClient que sera injetado onde necessario.
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }
}
