package com.br.luizalab.comunicacao.config;

import com.br.luizalab.comunicacao.dto.AgendamentoRequest;

import java.time.LocalDateTime;

public class Validacao {

    public static void validarRequest(AgendamentoRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("AgendamentoRequest não pode ser nulo.");
        }

        if (request.dataHoraAgendada() == null) {
            throw new IllegalArgumentException("Data e hora agendada não pode ser nula.");
        }

        if (request.dataHoraAgendada().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Data e hora agendada deve ser no futuro.");
        }

        if (request.destinatario() == null || request.destinatario().isBlank()) {
            throw new IllegalArgumentException("Destinatário não pode ser nulo ou vazio.");
        }

        if (request.mensagem() == null || request.mensagem().isBlank()) {
            throw new IllegalArgumentException("Mensagem não pode ser nula ou vazia.");
        }

        if (request.tipo() == null) {
            throw new IllegalArgumentException("Tipo de comunicação não pode ser nulo.");
        }
    }

    public static void validarId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo.");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("O ID deve ser um valor positivo.");
        }
    }
}
