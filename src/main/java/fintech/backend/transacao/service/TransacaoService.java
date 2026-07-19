package fintech.backend.transacao.service;

import fintech.backend.conta.entity.Conta;
import fintech.backend.categoria.entity.Categoria;
import fintech.backend.transacao.dto.ResumoDashboardDTO;
import fintech.backend.transacao.dto.TransacaoRequestDTO;
import fintech.backend.transacao.dto.TransacaoResponseDTO;
import fintech.backend.transacao.entity.Transacao;
import fintech.backend.exception.RecursoNaoEncontradoException;
import fintech.backend.categoria.repository.CategoriaRepository;
import fintech.backend.conta.repository.ContaRepository;
import fintech.backend.fatura.entity.Fatura;
import fintech.backend.fatura.repository.FaturaRepository;
import fintech.backend.transacao.repository.TransacaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    private final ContaRepository contaRepository;

    private final CategoriaRepository categoriaRepository;

    private final FaturaRepository faturaRepository;

    public TransacaoService(TransacaoRepository transacaoRepository, ContaRepository contaRepository,
            CategoriaRepository categoriaRepository, FaturaRepository faturaRepository) {
        this.transacaoRepository = transacaoRepository;
        this.contaRepository = contaRepository;
        this.categoriaRepository = categoriaRepository;
        this.faturaRepository = faturaRepository;
    }

    public Page<TransacaoResponseDTO> listar(Long contaId, Pageable pageable) {
        if (contaId != null) {
            return transacaoRepository.findByContaId(contaId, pageable).map(this::converterParaResponseDTO);
        }
        return transacaoRepository.findAll(pageable).map(this::converterParaResponseDTO);
    }

    public ResumoDashboardDTO obterResumo(Long contaId, String mesAno) {
        java.time.YearMonth ym = java.time.YearMonth.parse(mesAno);
        java.time.LocalDateTime inicio = ym.atDay(1).atStartOfDay();
        java.time.LocalDateTime fim = ym.atEndOfMonth().atTime(23, 59, 59);

        Double receitas = transacaoRepository.somarReceitas(contaId, inicio, fim);
        Double despesas = transacaoRepository.somarDespesas(contaId, inicio, fim);

        java.util.List<Object[]> resultadosCat = transacaoRepository.agruparGastosPorCategoria(contaId, inicio, fim);
        java.util.Map<String, Double> gastos = new java.util.HashMap<>();

        for (Object[] row : resultadosCat) {
            gastos.put((String) row[0], (Double) row[1]);
        }

        return new ResumoDashboardDTO(mesAno, contaId, receitas, despesas, gastos);
    }

    public TransacaoResponseDTO buscarPorId(Long id) {
        return converterParaResponseDTO(buscarEntidadePorId(id));
    }

    @Transactional
    public TransacaoResponseDTO criar(TransacaoRequestDTO requestDTO) {
        Transacao transacao = new Transacao();
        preencherTransacao(transacao, requestDTO);

        Conta conta = transacao.getConta();
        if (transacao.getTipoMovimentacao() == fintech.backend.transacao.entity.TipoMovimentacao.RECEITA) {
            conta.setSaldoAtual(conta.getSaldoAtual() + transacao.getValor());
        } else if (transacao.getTipoMovimentacao() == fintech.backend.transacao.entity.TipoMovimentacao.DESPESA) {
            conta.setSaldoAtual(conta.getSaldoAtual() - transacao.getValor());
        }
        contaRepository.save(conta);

        return converterParaResponseDTO(transacaoRepository.save(transacao));
    }

    @Transactional
    public TransacaoResponseDTO atualizar(Long id, TransacaoRequestDTO requestDTO) {
        Transacao transacao = buscarEntidadePorId(id);
        preencherTransacao(transacao, requestDTO);
        return converterParaResponseDTO(transacaoRepository.save(transacao));
    }

    @Transactional
    public void deletar(Long id) {
        Transacao transacao = buscarEntidadePorId(id);

        Conta conta = transacao.getConta();
        if (transacao.getTipoMovimentacao() == fintech.backend.transacao.entity.TipoMovimentacao.RECEITA) {
            conta.setSaldoAtual(conta.getSaldoAtual() - transacao.getValor());
        } else if (transacao.getTipoMovimentacao() == fintech.backend.transacao.entity.TipoMovimentacao.DESPESA) {
            conta.setSaldoAtual(conta.getSaldoAtual() + transacao.getValor());
        }
        contaRepository.save(conta);

        transacaoRepository.delete(transacao);
    }

    private void preencherTransacao(Transacao transacao, TransacaoRequestDTO requestDTO) {
        Conta conta = contaRepository.findById(requestDTO.getContaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Conta", requestDTO.getContaId()));
        Categoria categoria = categoriaRepository.findById(requestDTO.getCategoriaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria", requestDTO.getCategoriaId()));

        transacao.setTitulo(requestDTO.getTitulo());
        transacao.setValor(requestDTO.getValor());
        transacao.setDataHora(requestDTO.getDataHora());
        transacao.setOrigem(requestDTO.getOrigem());
        transacao.setTipoMovimentacao(requestDTO.getTipoMovimentacao());
        transacao.setTipoPagamento(requestDTO.getTipoPagamento());
        transacao.setStatus(requestDTO.getStatus());
        transacao.setConta(conta);
        transacao.setCategoria(categoria);

        if (requestDTO.getFaturaId() != null) {
            Fatura fatura = faturaRepository.findById(requestDTO.getFaturaId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Fatura", requestDTO.getFaturaId()));
            transacao.setFatura(fatura);
        } else {
            transacao.setFatura(null);
        }
    }

    private Transacao buscarEntidadePorId(Long id) {
        return transacaoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Transacao", id));
    }

    private TransacaoResponseDTO converterParaResponseDTO(Transacao transacao) {
        return new TransacaoResponseDTO(
                transacao.getId(),
                transacao.getTitulo(),
                transacao.getValor(),
                transacao.getDataHora(),
                transacao.getOrigem(),
                transacao.getTipoMovimentacao(),
                transacao.getTipoPagamento(),
                transacao.getStatus(),
                transacao.getConta().getId(),
                transacao.getConta().getDescricao(),
                transacao.getCategoria().getId(),
                transacao.getCategoria().getNome(),
                transacao.getFatura() != null ? transacao.getFatura().getId() : null,
                transacao.getFatura() != null ? transacao.getFatura().getMesAnoReferencia() : null);
    }
}
