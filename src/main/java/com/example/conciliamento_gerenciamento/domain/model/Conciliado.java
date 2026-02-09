package com.example.conciliamento_gerenciamento.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record Conciliado(
        UUID idTransacaoInterna,
        String idTransacaoBancaria,
        LocalDateTime dataConciliacao
) implements ResultadoConciliacao{

}
