package fintech.backend.config;

import fintech.backend.categoria.entity.Categoria;
import fintech.backend.categoria.repository.CategoriaRepository;
import fintech.backend.conta.entity.Conta;
import fintech.backend.conta.repository.ContaRepository;
import fintech.backend.usuario.entity.Usuario;
import fintech.backend.usuario.repository.UsuarioRepository;
import fintech.backend.transacao.entity.Transacao;
import fintech.backend.transacao.entity.TipoMovimentacao;
import fintech.backend.transacao.entity.TipoPagamento;
import fintech.backend.transacao.entity.OrigemTransacao;
import fintech.backend.transacao.entity.StatusTransacao;
import fintech.backend.transacao.repository.TransacaoRepository;
import fintech.backend.metaorcamento.entity.MetaOrcamento;
import fintech.backend.metaorcamento.repository.MetaOrcamentoRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(CategoriaRepository categoriaRepository, 
                                   ContaRepository contaRepository,
                                   UsuarioRepository usuarioRepository,
                                   TransacaoRepository transacaoRepository,
                                   MetaOrcamentoRepository metaOrcamentoRepository) {
        return args -> {
            
            // Limpa tudo para garantir um banco novo toda vez que reiniciar
            transacaoRepository.deleteAll();
            metaOrcamentoRepository.deleteAll();
            contaRepository.deleteAll();
            categoriaRepository.deleteAll();
            usuarioRepository.deleteAll();
            
            // Seed de Usuários
            Usuario u1 = new Usuario();
            u1.setNome("Usuário Teste");
            u1.setEmail("user@fintech.com");
            u1.setSenhaHash("1234");
            usuarioRepository.save(u1);

            Usuario u2 = new Usuario();
            u2.setNome("Administrador");
            u2.setEmail("admin@fintech.com");
            u2.setSenhaHash("1234");
            usuarioRepository.save(u2);

            Usuario usuario = usuarioRepository.findByEmail("user@fintech.com").orElse(null);
            if (usuario == null) return;

            // Seed de Contas
            Conta conta1 = new Conta();
            conta1.setDescricao("Conta Nubank");
            conta1.setSaldoAtual(15000.0);
            conta1.setAtiva(true);
            conta1.setUsuario(usuario);
            contaRepository.save(conta1);

            Conta cartao1 = new Conta();
            cartao1.setDescricao("Cartão Mercado Pago");
            cartao1.setSaldoAtual(2850.75);
            cartao1.setAtiva(true);
            cartao1.setUsuario(usuario);
            contaRepository.save(cartao1);

            // Seed de Categorias
            String[][] cats = {
                {"Alimentação", "#EF4444"},
                {"Transporte", "#F59E0B"},
                {"Lazer", "#8B5CF6"},
                {"Saúde", "#10B981"},
                {"Moradia", "#3B82F6"},
                {"Salário", "#10B981"},
                {"Investimentos", "#8B5CF6"}
            };
            for (String[] c : cats) {
                Categoria cat = new Categoria();
                cat.setNome(c[0]);
                cat.setCorHexadecimal(c[1]);
                cat.setAtiva(true);
                cat.setUsuario(usuario);
                categoriaRepository.save(cat);
            }

            // Carregar as contas e categorias recém-criadas
            List<Conta> contas = contaRepository.findAll();
            List<Categoria> categorias = categoriaRepository.findAll();
            if (contas.isEmpty() || categorias.isEmpty()) return;

            Conta contaPrincipal = contas.get(0);
            Categoria catSalario = categorias.stream().filter(c -> c.getNome().equals("Salário")).findFirst().orElse(categorias.get(0));
            Categoria catAlimentacao = categorias.stream().filter(c -> c.getNome().equals("Alimentação")).findFirst().orElse(categorias.get(0));
            Categoria catMoradia = categorias.stream().filter(c -> c.getNome().equals("Moradia")).findFirst().orElse(categorias.get(0));

            // Seed de Metas de Orçamento
            String mesAtual = LocalDateTime.now().getYear() + "-" + String.format("%02d", LocalDateTime.now().getMonthValue());
            
            MetaOrcamento m1 = new MetaOrcamento();
            m1.setCategoria(catAlimentacao);
            m1.setValorLimite(1500.0);
            m1.setMesAnoReferencia(mesAtual);
            metaOrcamentoRepository.save(m1);

            MetaOrcamento m2 = new MetaOrcamento();
            m2.setCategoria(catMoradia);
            m2.setValorLimite(2500.0);
            m2.setMesAnoReferencia(mesAtual);
            metaOrcamentoRepository.save(m2);

            // Seed de Transações
            // Receita
            Transacao t1 = new Transacao();
            t1.setTitulo("Salário Mensal");
            t1.setValor(8500.0);
            t1.setDataHora(LocalDateTime.now().minusDays(5));
            t1.setOrigem(OrigemTransacao.API_MOCKADA);
            t1.setTipoMovimentacao(TipoMovimentacao.RECEITA);
            t1.setTipoPagamento(TipoPagamento.PIX);
            t1.setStatus(StatusTransacao.CONCLUIDA);
            t1.setConta(contaPrincipal);
            t1.setCategoria(catSalario);
            transacaoRepository.save(t1);

            // Despesa
            Transacao t2 = new Transacao();
            t2.setTitulo("Supermercado Extra");
            t2.setValor(850.0);
            t2.setDataHora(LocalDateTime.now().minusDays(2));
            t2.setOrigem(OrigemTransacao.API_MOCKADA);
            t2.setTipoMovimentacao(TipoMovimentacao.DESPESA);
            t2.setTipoPagamento(TipoPagamento.CARTAO_DEBITO);
            t2.setStatus(StatusTransacao.CONCLUIDA);
            t2.setConta(contaPrincipal);
            t2.setCategoria(catAlimentacao);
            transacaoRepository.save(t2);

            // Despesa
            Transacao t3 = new Transacao();
            t3.setTitulo("Aluguel");
            t3.setValor(2200.0);
            t3.setDataHora(LocalDateTime.now().minusDays(4));
            t3.setOrigem(OrigemTransacao.API_MOCKADA);
            t3.setTipoMovimentacao(TipoMovimentacao.DESPESA);
            t3.setTipoPagamento(TipoPagamento.PIX);
            t3.setStatus(StatusTransacao.CONCLUIDA);
            t3.setConta(contaPrincipal);
            t3.setCategoria(catMoradia);
            transacaoRepository.save(t3);
            
            // Despesa Hoje
            Transacao t4 = new Transacao();
            t4.setTitulo("Restaurante Outback");
            t4.setValor(240.0);
            t4.setDataHora(LocalDateTime.now());
            t4.setOrigem(OrigemTransacao.API_MOCKADA);
            t4.setTipoMovimentacao(TipoMovimentacao.DESPESA);
            t4.setTipoPagamento(TipoPagamento.CARTAO_CREDITO);
            t4.setStatus(StatusTransacao.CONCLUIDA);
            t4.setConta(contaPrincipal);
            t4.setCategoria(catAlimentacao);
            transacaoRepository.save(t4);
        };
    }
}
