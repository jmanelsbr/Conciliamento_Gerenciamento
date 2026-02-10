package com.example.conciliamento_gerenciamento.domain.repository;

import com.example.conciliamento_gerenciamento.domain.model.StatusConciliacao;
import com.example.conciliamento_gerenciamento.domain.model.TransacaoBancaria;
import com.example.conciliamento_gerenciamento.domain.model.TransacaoInterna;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface TransacaoInternaRepository {

    Optional<TransacaoInterna> buscaPorId(UUID id);

    Stream<TransacaoInterna> buscaPorStatus(StatusConciliacao status);

    TransacaoInterna salvar(TransacaoInterna transacao);

}