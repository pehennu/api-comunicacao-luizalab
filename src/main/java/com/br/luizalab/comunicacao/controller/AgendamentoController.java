package com.br.luizalab.comunicacao.controller;

import com.br.luizalab.comunicacao.dto.AgendamentoRequest;
import com.br.luizalab.comunicacao.dto.AgendamentoResponse;
import com.br.luizalab.comunicacao.service.AgendamentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@Tag(name = "Agendamentos", description = "Gerenciamento de agendamentos de comunicação")
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @Operation(summary = "Cria um novo agendamento", description = "Cria um novo agendamento de comunicação.")
    @PostMapping("/agendar")
    public ResponseEntity<AgendamentoResponse> criar(@RequestBody @Valid AgendamentoRequest request) {
        log.info("Requisição recebida para criar agendamento: {}", request);

        var response = service.criarAgendamento(request);

        log.info("Agendamento criado com sucesso: {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/buscar_agendamento/{id}")
    public ResponseEntity<Map<String, Object>> buscar(@PathVariable @Valid Long id) {
        log.info("Requisição recebida para buscar agendamento com ID: {}", id);
        var response = service.buscarAgendamento(id); // Pode lançar a exceção
        log.info("Agendamento encontrado: {}", response);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("agendamento", response);
        return ResponseEntity.ok(responseBody);
    }

    @Operation(summary = "Deleta um agendamento por ID", description = "Deleta um agendamento existente.")
    @DeleteMapping("/deletar_agendamento/{id}")
    public ResponseEntity<Map<String, String>> deletar(@PathVariable @Valid Long id) {
        log.info("Requisição recebida para deletar agendamento com ID: {}", id);
        service.deletarAgendamento(id);
        log.info("Agendamento com ID {} deletado com sucesso", id);

        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Agendamento com ID " + id + " deletado com sucesso.");
        return ResponseEntity.ok(response);
    }
}


