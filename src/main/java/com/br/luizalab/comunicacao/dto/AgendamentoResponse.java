package com.br.luizalab.comunicacao.dto;

import com.br.luizalab.comunicacao.model.StatusAgendamento;
import com.br.luizalab.comunicacao.model.TipoComunicacao;

import java.time.LocalDateTime;

public record AgendamentoResponse(
        Long id,
        LocalDateTime dataHoraAgendada,
        String destinatario,
        String mensagem,
        TipoComunicacao tipo,
        StatusAgendamento status,
        LocalDateTime criadoEm
) {}
