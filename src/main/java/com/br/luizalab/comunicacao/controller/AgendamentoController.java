package com.br.luizalab.comunicacao.controller;

import com.br.luizalab.comunicacao.dto.AgendamentoRequest;
import com.br.luizalab.comunicacao.service.AgendamentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

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
    public ResponseEntity<?> criar(@RequestBody @Valid AgendamentoRequest request) {
        log.info("Requisição recebida para criar agendamento: {}", request);
        try {
            var response = service.criar(request);
            log.info("Agendamento criado com sucesso: {}", response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Erro ao criar agendamento", e);
            return ResponseEntity.badRequest().body("Erro ao criar agendamento: " + e.getMessage());
        }
    }

    @Operation(summary = "Busca um agendamento por ID", description = "Busca um agendamento existente.")
    @GetMapping("/buscar_agendamento/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        log.info("Requisição recebida para buscar agendamento com ID: {}", id);
        try {
            var response = service.buscar(id);
            log.info("Agendamento encontrado: {}", response);
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            log.warn("Agendamento com ID {} não encontrado", id);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Erro ao buscar agendamento", e);
            return ResponseEntity.internalServerError().body("Erro ao buscar agendamento: " + e.getMessage());
        }
    }

    @Operation(summary = "Deleta um agendamento por ID", description = "Deleta um agendamento existente.")
    @DeleteMapping("/deletar_agendamento/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        log.info("Requisição recebida para deletar agendamento com ID: {}", id);
        try {
            service.deletar(id);
            log.info("Agendamento com ID {} deletado com sucesso", id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            log.warn("Agendamento com ID {} não encontrado para exclusão", id);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Erro ao deletar agendamento", e);
            return ResponseEntity.internalServerError().body("Erro ao deletar agendamento: " + e.getMessage());
        }
    }
}


