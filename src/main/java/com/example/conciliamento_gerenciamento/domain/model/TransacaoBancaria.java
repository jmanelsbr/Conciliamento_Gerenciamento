package com.example.conciliamento_gerenciamento.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoBancaria(
        String idTransacaoBancaria,
        String descricao,
        BigDecimal valorReal,
        LocalDateTime dataTBancaria
) {
}
