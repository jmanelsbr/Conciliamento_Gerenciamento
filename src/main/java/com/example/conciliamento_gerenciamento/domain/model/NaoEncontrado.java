package com.example.conciliamento_gerenciamento.domain.model;

import java.util.UUID;

public record NaoEncontrado(
        UUID idTransacaoInterna,
        String idTransacaoBancaria,
        String motivo
) implements ResultadoConciliacao {
}
