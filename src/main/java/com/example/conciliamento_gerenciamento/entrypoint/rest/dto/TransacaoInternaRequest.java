package com.example.conciliamento_gerenciamento.entrypoint.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoInternaRequest(
        String descricao,
        BigDecimal valorPrevisto,
        LocalDateTime dataTransacao
) {


}
