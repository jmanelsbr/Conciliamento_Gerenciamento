package com.example.conciliamento_gerenciamento.domain.model;

import java.util.UUID;

public record NaoEncontrado(
        UUID idTransacaoInterna,
        String idTransacaoBancaria,
        String Motivo
) implements ResultadoConciliacao {
}
