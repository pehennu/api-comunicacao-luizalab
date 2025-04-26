package com.br.luizalab.comunicacao.mapper;

import com.br.luizalab.comunicacao.dto.AgendamentoResponse;
import com.br.luizalab.comunicacao.model.AgendamentoComunicacao;

public class AgendamentoMapper {
    public static AgendamentoResponse toResponse(AgendamentoComunicacao a) {
        return new AgendamentoResponse(
                a.getId(),
                a.getDataHoraAgendada(),
                a.getDestinatario(),
                a.getMensagem(),
                a.getTipo(),
                a.getStatus(),
                a.getCriadoEm()
        );
    }
}
