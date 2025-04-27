package com.br.luizalab.comunicacao.exception;

public class AgendamentoNaoEncontradoException extends RuntimeException {
    public AgendamentoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
