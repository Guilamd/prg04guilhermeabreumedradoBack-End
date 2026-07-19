package fintech.backend.config;

import fintech.backend.categoria.entity.Categoria;
import fintech.backend.categoria.repository.CategoriaRepository;
import fintech.backend.conta.entity.Conta;
import fintech.backend.conta.repository.ContaRepository;
import fintech.backend.usuario.entity.Usuario;
import fintech.backend.usuario.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(CategoriaRepository categoriaRepository, 
                                   ContaRepository contaRepository,
                                   UsuarioRepository usuarioRepository) {
        return args -> {
            // Pegar o primeiro usuário para atrelar os dados padrão
            Usuario usuario = usuarioRepository.findAll().stream().findFirst().orElse(null);
            if (usuario == null) {
                return; // Se não tem usuário, não sementa nada
            }

            // Sementar Contas se estiver vazio
            if (contaRepository.count() == 0) {
                Conta conta = new Conta();
                conta.setDescricao("Conta Corrente");
                conta.setSaldoAtual(1000.0);
                conta.setAtiva(true);
                conta.setUsuario(usuario);
                contaRepository.save(conta);

                Conta cartao = new Conta();
                cartao.setDescricao("Cartão de Crédito");
                cartao.setSaldoAtual(0.0);
                cartao.setAtiva(true);
                cartao.setUsuario(usuario);
                contaRepository.save(cartao);
            }

            // Sementar Categorias se estiver vazio
            if (categoriaRepository.count() == 0) {
                String[] nomesCategorias = {"Alimentação", "Transporte", "Lazer", "Saúde", "Educação", "Moradia", "Salário", "Investimentos"};
                for (String nome : nomesCategorias) {
                    Categoria cat = new Categoria();
                    cat.setNome(nome);
                    cat.setCorHexadecimal("#8B5CF6");
                    cat.setAtiva(true);
                    cat.setUsuario(usuario);
                    categoriaRepository.save(cat);
                }
            }
        };
    }
}
