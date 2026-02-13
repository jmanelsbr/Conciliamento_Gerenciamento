package com.example.conciliamento_gerenciamento.infrastructure.repository;

import com.example.conciliamento_gerenciamento.domain.model.StatusConciliacao;
import com.example.conciliamento_gerenciamento.domain.model.TransacaoInterna;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
public class TransacaoInternaRepositoryIT {
    final private TransacaoInternaRepositoryImpl transacaoInternaRepositoryImpl;

    @Autowired
    public TransacaoInternaRepositoryIT(TransacaoInternaRepositoryImpl transacaoInternaRepositoryImpl) {
        this.transacaoInternaRepositoryImpl = transacaoInternaRepositoryImpl;
    }

    @Test
    @Transactional
    public void deveSalvarEBucarTransacaoComSucesso(){
        UUID idSalva = UUID.randomUUID();

        TransacaoInterna tInterna = new TransacaoInterna(
                idSalva, "Venda",
                new BigDecimal("100.00"),
                LocalDateTime.now(),
                StatusConciliacao.PENDENTE);

        transacaoInternaRepositoryImpl.salvar(tInterna);
        Optional<TransacaoInterna> buscaPorId = transacaoInternaRepositoryImpl.buscaPorId(tInterna.idTransacaoInterna());

        assertThat(buscaPorId.get().idTransacaoInterna()).isEqualTo(idSalva);
    }




}

