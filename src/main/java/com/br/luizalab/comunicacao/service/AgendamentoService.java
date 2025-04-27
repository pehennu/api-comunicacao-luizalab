package com.br.luizalab.comunicacao.service;

import com.br.luizalab.comunicacao.config.Validacao;
import com.br.luizalab.comunicacao.dto.AgendamentoRequest;
import com.br.luizalab.comunicacao.dto.AgendamentoResponse;
import com.br.luizalab.comunicacao.exception.AgendamentoNaoEncontradoException;
import com.br.luizalab.comunicacao.mapper.AgendamentoMapper;
import com.br.luizalab.comunicacao.model.AgendamentoComunicacao;
import com.br.luizalab.comunicacao.repository.AgendamentoComunicacaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class AgendamentoService {

    private final AgendamentoComunicacaoRepository repositorio;

    public AgendamentoService(AgendamentoComunicacaoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Transactional
    public AgendamentoResponse criarAgendamento(AgendamentoRequest request) {
        Validacao.validarRequest(request);
        log.info("Iniciando criação de agendamento: {}", request);
        var agendamento = new AgendamentoComunicacao();
        agendamento.setDataHoraAgendada(request.dataHoraAgendada());
        agendamento.setDestinatario(request.destinatario());
        agendamento.setMensagem(request.mensagem());
        agendamento.setTipo(request.tipo());

        AgendamentoComunicacao salvo = repositorio.save(agendamento);
        return AgendamentoMapper.toResponse(salvo);
    }

    public AgendamentoResponse buscarAgendamento(Long id) {
        Validacao.validarId(id);
        log.info("Buscando agendamento com ID: {}", id);

        Optional<AgendamentoComunicacao> agendamentoOpt = repositorio.findById(id);

        if (agendamentoOpt.isEmpty()) {
            log.warn("Agendamento com ID {} não encontrado.", id);
            throw new AgendamentoNaoEncontradoException("Agendamento com ID " + id + " não encontrado.");
        }

        return AgendamentoMapper.toResponse(agendamentoOpt.get());
    }

    public void deletarAgendamento(Long id) {
        Validacao.validarId(id);
        log.info("Iniciando deleção de agendamento com ID: {}", id);

        if (!repositorio.existsById(id)) {
            throw new AgendamentoNaoEncontradoException("Agendamento com ID " + id + " não encontrado para exclusão.");
        }

        repositorio.deleteById(id);
    }
}
