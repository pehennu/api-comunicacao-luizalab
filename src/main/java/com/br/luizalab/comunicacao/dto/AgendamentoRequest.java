package com.br.luizalab.comunicacao.dto;

import com.br.luizalab.comunicacao.model.TipoComunicacao;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoRequest(
        @NotNull @Future LocalDateTime dataHoraAgendada,
        @NotBlank String destinatario,
        @NotBlank String mensagem,
        @NotNull TipoComunicacao tipo
) {}
