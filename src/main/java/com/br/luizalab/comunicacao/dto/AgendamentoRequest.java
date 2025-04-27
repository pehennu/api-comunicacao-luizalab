package com.br.luizalab.comunicacao.dto;

import com.br.luizalab.comunicacao.model.TipoComunicacao;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AgendamentoRequest(
        @NotNull(message = "Data e hora agendada não pode ser nula.")
        @Future(message = "Data e hora agendada deve ser no futuro.")
        LocalDateTime dataHoraAgendada,

        @NotBlank(message = "Destinatário não pode ser nulo ou vazio.")
        @Size(max = 100, message = "Destinatário não pode ter mais de 100 caracteres.")
        String destinatario,

        @NotBlank(message = "Mensagem não pode ser nula ou vazia.")
        @Size(max = 500, message = "Mensagem não pode ter mais de 500 caracteres.")
        String mensagem,

        @NotNull(message = "Tipo de comunicação não pode ser nulo.")
        TipoComunicacao tipo
) {}
