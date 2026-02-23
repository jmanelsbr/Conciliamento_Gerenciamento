package com.example.conciliamento_gerenciamento.entrypoint.rest.mapper;

import com.example.conciliamento_gerenciamento.domain.model.StatusConciliacao;
import com.example.conciliamento_gerenciamento.domain.model.TransacaoInterna;
import com.example.conciliamento_gerenciamento.entrypoint.rest.dto.TransacaoInternaRequest;
import com.example.conciliamento_gerenciamento.entrypoint.rest.dto.TransacaoInternaResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TransacaoInternaMapper {

    public TransacaoInterna toEntity(TransacaoInternaRequest request){

        UUID idTransacaoInterna = UUID.randomUUID();
        StatusConciliacao status = StatusConciliacao.PENDENTE;
        return new TransacaoInterna(
                idTransacaoInterna,
                request.descricao(),
                request.valorPrevisto(),
                request.dataTransacao(),
                status);
    }

    public TransacaoInternaResponse toResponse(TransacaoInterna transacaoInterna){
        return new TransacaoInternaResponse(
                transacaoInterna.idTransacaoInterna(),
                transacaoInterna.descricao(),
                transacaoInterna.valorPrevisto(),
                transacaoInterna.dataTransacao(),
                transacaoInterna.status());
    }
}
