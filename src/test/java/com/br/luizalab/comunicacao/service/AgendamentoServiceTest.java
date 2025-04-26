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
    void deveCriarAgendamento() {
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

        var response = service.criar(request);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("teste@email.com", response.destinatario());
        verify(repository, times(1)).save(any());
    }

    @Test
    void deveBuscarAgendamentoPorId() {
        var agendamento = new AgendamentoComunicacao();
        agendamento.setId(10L);
        agendamento.setDestinatario("alguem@dominio.com");

        when(repository.findById(10L)).thenReturn(Optional.of(agendamento));

        var result = service.buscar(10L);

        assertEquals(10L, result.id());
        assertEquals("alguem@dominio.com", result.destinatario());
    }

    @Test
    void deveDeletarAgendamento() {
        when(repository.existsById(5L)).thenReturn(true);

        service.deletar(5L);

        verify(repository).deleteById(5L);
    }

    @Test
    void deveLancarExcecaoAoDeletarIdInexistente() {
        when(repository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> service.deletar(99L));
    }
}
