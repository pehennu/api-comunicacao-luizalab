package com.br.luizalab.comunicacao.controller;

import com.br.luizalab.comunicacao.dto.AgendamentoRequest;
import com.br.luizalab.comunicacao.dto.AgendamentoResponse;
import com.br.luizalab.comunicacao.model.StatusAgendamento;
import com.br.luizalab.comunicacao.model.TipoComunicacao;
import com.br.luizalab.comunicacao.service.AgendamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AgendamentoControllerTest {

    @Mock
    private AgendamentoService service;

    @InjectMocks
    private AgendamentoController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarAgendamento() {
        var request = new AgendamentoRequest(
                LocalDateTime.now().plusHours(1),
                "teste@magalu.com",
                "Mensagem de teste",
                TipoComunicacao.EMAIL
        );

        var response = new AgendamentoResponse(
                1L,
                request.dataHoraAgendada(),
                request.destinatario(),
                request.mensagem(),
                request.tipo(),
                StatusAgendamento.AGENDADO,
                LocalDateTime.now()
        );

        when(service.criar(any())).thenReturn(response);

        ResponseEntity<AgendamentoResponse> resultado = (ResponseEntity<AgendamentoResponse>) controller.criar(request);

        assertEquals(200, resultado.getStatusCode());
        assertEquals(response.id(), Objects.requireNonNull(resultado.getBody()).id());
        verify(service).criar(request);
    }

    @Test
    void deveBuscarAgendamentoPorId() {
        var response = new AgendamentoResponse(
                2L,
                LocalDateTime.now().plusHours(2),
                "buscar@magalu.com",
                "Mensagem",
                TipoComunicacao.SMS,
                StatusAgendamento.AGENDADO,
                LocalDateTime.now()
        );

        when(service.buscar(2L)).thenReturn(response);

        ResponseEntity<AgendamentoResponse> resultado = (ResponseEntity<AgendamentoResponse>) controller.buscar(2L);

        assertEquals(200, resultado.getStatusCode());
        assertEquals("buscar@magalu.com", Objects.requireNonNull(resultado.getBody()).destinatario());
        verify(service).buscar(2L);
    }

    @Test
    void deveDeletarAgendamento() {
        doNothing().when(service).deletar(3L);

        ResponseEntity<Void> resultado = (ResponseEntity<Void>) controller.deletar(3L);

        assertEquals(204, resultado.getStatusCode());
        verify(service).deletar(3L);
    }
}

