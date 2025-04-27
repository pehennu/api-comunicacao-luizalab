package com.br.luizalab.comunicacao.controller;

import com.br.luizalab.comunicacao.dto.AgendamentoRequest;
import com.br.luizalab.comunicacao.dto.AgendamentoResponse;
import com.br.luizalab.comunicacao.exception.AgendamentoNaoEncontradoException;
import com.br.luizalab.comunicacao.model.StatusAgendamento;
import com.br.luizalab.comunicacao.model.TipoComunicacao;
import com.br.luizalab.comunicacao.service.AgendamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

        when(service.criarAgendamento(any())).thenReturn(response);

        ResponseEntity<AgendamentoResponse> resultado = (ResponseEntity<AgendamentoResponse>) controller.criar(request);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(response.id(), Objects.requireNonNull(resultado.getBody()).id());
        verify(service).criarAgendamento(request);
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

        when(service.buscarAgendamento(2L)).thenReturn(response);

        ResponseEntity<Map<String, Object>> resultado = (ResponseEntity<Map<String, Object>>) controller.buscar(2L);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(response, Objects.requireNonNull(resultado.getBody()).get("agendamento"));
        verify(service).buscarAgendamento(2L);
    }

    @Test
    void deveDeletarAgendamento() {
        doNothing().when(service).deletarAgendamento(3L);

        ResponseEntity<Map<String, String>> resultado = (ResponseEntity<Map<String, String>>) controller.deletar(3L);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals("Agendamento com ID 3 deletado com sucesso.", resultado.getBody().get("mensagem"));
        verify(service).deletarAgendamento(3L);
    }

}

