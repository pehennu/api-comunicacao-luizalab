package com.br.luizalab.comunicacao.service;

import com.br.luizalab.comunicacao.dto.AgendamentoRequest;
import com.br.luizalab.comunicacao.dto.AgendamentoResponse;
import com.br.luizalab.comunicacao.mapper.AgendamentoMapper;
import com.br.luizalab.comunicacao.model.AgendamentoComunicacao;
import com.br.luizalab.comunicacao.repository.AgendamentoComunicacaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@Service
public class AgendamentoService {

    private final AgendamentoComunicacaoRepository repositorio;

    public AgendamentoService(AgendamentoComunicacaoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Transactional
    public AgendamentoResponse criar(AgendamentoRequest request) {
        log.info("Iniciando criação de agendamento: {}", request);
        var agendamento = new AgendamentoComunicacao();
        agendamento.setDataHoraAgendada(request.dataHoraAgendada());
        agendamento.setDestinatario(request.destinatario());
        agendamento.setMensagem(request.mensagem());
        agendamento.setTipo(request.tipo());

        AgendamentoComunicacao salvo = repositorio.save(agendamento);
        return AgendamentoMapper.toResponse(salvo);
    }

    public AgendamentoResponse buscar(Long id) {
        log.info("Buscando agendamento com ID: {}", id);
        var agendamento = repositorio.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Agendamento não encontrado."));
        return AgendamentoMapper.toResponse(agendamento);
    }

    public void deletar(Long id) {
        log.info("Iniciando deleção de agendamento com ID: {}", id);
        if (!repositorio.existsById(id)) {
            throw new NoSuchElementException("Agendamento não encontrado para exclusão.");
        }
        repositorio.deleteById(id);
    }
}
