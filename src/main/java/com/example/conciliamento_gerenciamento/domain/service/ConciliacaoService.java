package com.example.conciliamento_gerenciamento.domain.service;

import com.example.conciliamento_gerenciamento.domain.exception.ValidacaoDominioException;
import com.example.conciliamento_gerenciamento.domain.model.*;
import com.example.conciliamento_gerenciamento.domain.repository.TransacaoInternaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class ConciliacaoService {

    private static final Logger logger = LoggerFactory.getLogger(ConciliacaoService.class);

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

    void processarConciliacao( TransacaoBancaria tBancaria) {
        Optional.ofNullable(tBancaria)
                .orElseThrow(() -> new ValidacaoDominioException("A transação bancária é obrigatória para o processamento."));

        logger.info("Iniciando processamento de conciliação para a transação bancária: {}", tBancaria.idTransacaoBancaria());

        this.transacaoInternaRepository
                .buscaPorStatus(StatusConciliacao.PENDENTE)
                .forEach(  tInterna ->
                {
                    Optional.ofNullable(tInterna)
                            .orElseThrow(() -> new ValidacaoDominioException("A transação interna é obrigatória para o processamento."));

                    var resultado = conciliacao(tInterna, tBancaria);
                    switch (resultado){
                        case Conciliado c -> {
                            transacaoInternaRepository.salvar(tInterna.comStatus(StatusConciliacao.CONCILIADO));
                            logger.info("Transação {} conciliada com sucesso.", tInterna.idTransacaoInterna());
                        }
                        default-> {
                            transacaoInternaRepository.salvar(tInterna.comStatus(StatusConciliacao.DIVERGENTE));
                            logger.warn("TRANSAÇÃO {} APRESENTA DIVERGÊNCIAS.  ", tInterna.idTransacaoInterna());
                        }

                    }
                });

    }
}
