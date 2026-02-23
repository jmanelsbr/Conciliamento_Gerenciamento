package com.example.conciliamento_gerenciamento.entrypoint.rest.dto;

import com.example.conciliamento_gerenciamento.domain.model.StatusConciliacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransacaoInternaResponse(
        UUID idTransacaoInterna,
        String descricao,
        BigDecimal valorPrevisto,
        LocalDateTime dataTransacao,
        StatusConciliacao status

) {
}
