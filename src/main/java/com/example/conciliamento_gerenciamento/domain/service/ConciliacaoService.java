package com.example.conciliamento_gerenciamento.domain.service;

import com.example.conciliamento_gerenciamento.domain.model.*;
import com.example.conciliamento_gerenciamento.domain.repository.TransacaoInternaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ConciliacaoService {

   private TransacaoInternaRepository transacaoInternaRepository;

    public ConciliacaoService (TransacaoInternaRepository transacaoInternaRepository){
        this.transacaoInternaRepository = transacaoInternaRepository;
    }
    ResultadoConciliacao conciliacao(TransacaoInterna tInterna, TransacaoBancaria tBancaria){
     var resultado = tInterna.valorPrevisto().compareTo(tBancaria.valorReal());

     switch (resultado){
         case 0 -> {
             return new Conciliado(tInterna.idTransacaoInterna(), tBancaria.idTransacaoBancaria(), LocalDateTime.now());
         }

         default -> {
             BigDecimal divergencia = tInterna.valorPrevisto().subtract(tBancaria.valorReal());
             return new Divergente(tInterna.idTransacaoInterna(),tBancaria.idTransacaoBancaria(), tInterna.valorPrevisto(), tBancaria.valorReal(), divergencia);
         }
     }
    }

    void processarConciliacao( TransacaoBancaria tBancaria){
        this.transacaoInternaRepository
                .buscaPorStatus(StatusConciliacao.PENDENTE)
                .forEach(  tInterna ->
                { var resultado = conciliacao(tInterna, tBancaria);
                    switch (resultado){
                        case Conciliado c -> transacaoInternaRepository.salvar(tInterna.comStatus(StatusConciliacao.CONCILIADO));
                        default-> transacaoInternaRepository.salvar(tInterna.comStatus(StatusConciliacao.DIVERGENTE));

                    }
                });

    }
}
