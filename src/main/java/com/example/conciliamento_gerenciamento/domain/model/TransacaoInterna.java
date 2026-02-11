package com.example.conciliamento_gerenciamento.domain.model;

import com.example.conciliamento_gerenciamento.domain.exception.ValidacaoDominioException;

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

    public TransacaoInterna{
       if (valorPrevisto == null || valorPrevisto.compareTo(BigDecimal.ZERO) <= 0){
           throw new ValidacaoDominioException("O valor previsto deve ser positivo e não nulo.");
       }
    }

}
