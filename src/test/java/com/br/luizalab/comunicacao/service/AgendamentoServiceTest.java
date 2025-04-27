package com.br.luizalab.comunicacao.service;

import com.br.luizalab.comunicacao.dto.AgendamentoRequest;
import com.br.luizalab.comunicacao.model.AgendamentoComunicacao;
import com.br.luizalab.comunicacao.model.TipoComunicacao;
import com.br.luizalab.comunicacao.repository.AgendamentoComunicacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AgendamentoServiceTest {
    private AgendamentoComunicacaoRepository repository;
    private AgendamentoService service;

    @BeforeEach
    void setUp() {
        repository = mock(AgendamentoComunicacaoRepository.class);
        service = new AgendamentoService(repository);
    }

    @Test
    void deveCriarAgendamentoAgendamento() {
        var request = new AgendamentoRequest(
                LocalDateTime.now().plusHours(1),
                "teste@email.com",
                "Mensagem teste",
                TipoComunicacao.EMAIL
        );

        var entidadeSalva = new AgendamentoComunicacao();
        entidadeSalva.setId(1L);
        entidadeSalva.setDataHoraAgendada(request.dataHoraAgendada());
        entidadeSalva.setDestinatario(request.destinatario());
        entidadeSalva.setMensagem(request.mensagem());
        entidadeSalva.setTipo(request.tipo());

        when(repository.save(Mockito.any())).thenReturn(entidadeSalva);

        var response = service.criarAgendamento(request);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("teste@email.com", response.destinatario());
        verify(repository, times(1)).save(any());
    }

    @Test
    void deveBuscarAgendamentoAgendamentoPorId() {
        var agendamento = new AgendamentoComunicacao();
        agendamento.setId(10L);
        agendamento.setDestinatario("alguem@dominio.com");

        when(repository.findById(10L)).thenReturn(Optional.of(agendamento));

        var result = service.buscarAgendamento(10L);

        assertEquals(10L, result.id());
        assertEquals("alguem@dominio.com", result.destinatario());
    }

    @Test
    void deveDeletarAgendamentoAgendamento() {
        when(repository.existsById(5L)).thenReturn(true);

        service.deletarAgendamento(5L);

        verify(repository).deleteById(5L);
    }

    @Test
    void deveLancarExcecaoAoDeletarAgendamentoIdInexistente() {
        when(repository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> service.deletarAgendamento(99L));
    }

    @Test
    void deveLancarExcecaoAoCriarAgendamentoComDataPassada() {
        var request = new AgendamentoRequest(
                LocalDateTime.now().minusDays(1), // Data no passado
                "destinatario@exemplo.com",
                "Mensagem de teste",
                TipoComunicacao.EMAIL
        );

        assertThrows(IllegalArgumentException.class, () -> service.criarAgendamento(request));
    }

    @Test
    void deveLancarExcecaoAoBuscarAgendamentoComIdNulo() {
        assertThrows(IllegalArgumentException.class, () -> service.buscarAgendamento(null));
    }

    @Test
    void deveLancarExcecaoAoDeletarAgendamentoComIdNulo() {
        assertThrows(IllegalArgumentException.class, () -> service.deletarAgendamento(null));
    }
}
