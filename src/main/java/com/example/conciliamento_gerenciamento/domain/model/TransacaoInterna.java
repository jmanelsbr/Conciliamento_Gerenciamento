package com.example.conciliamento_gerenciamento.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransacaoInterna(
        UUID idTransacaoInterna,
        String descricao,
        BigDecimal valorPrevisto,
        LocalDateTime dataTransacao,
        StatusConciliacao status
){
   public TransacaoInterna comStatus(StatusConciliacao novoStatus){
        return new TransacaoInterna(
                this.idTransacaoInterna(),
                this.descricao(),
                this.valorPrevisto(),
                this.dataTransacao(),
                novoStatus
        );
    }

}
