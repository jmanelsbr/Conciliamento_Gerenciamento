package com.example.conciliamento_gerenciamento.infrastructure.repository;

import com.example.conciliamento_gerenciamento.domain.model.StatusConciliacao;
import com.example.conciliamento_gerenciamento.domain.model.TransacaoInterna;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import com.example.conciliamento_gerenciamento.TestcontainersConfiguration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

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
                new BigDecimal("200.00"),
                LocalDateTime.now(),
                StatusConciliacao.PENDENTE);

        transacaoInternaRepositoryImpl.salvar(tInterna);
        Optional<TransacaoInterna> buscaPorId = transacaoInternaRepositoryImpl.buscaPorId(tInterna.idTransacaoInterna());

        assertThat(buscaPorId).isPresent();
        assertThat(buscaPorId.get().idTransacaoInterna()).isEqualTo(idSalva);

    }

    @Test
    @Transactional
    public void deveBuscarPorStatusComSucesso() {
        TransacaoInterna tInterna = new TransacaoInterna(
                UUID.randomUUID(), "Venda",
                new BigDecimal("100.00"),
                LocalDateTime.now(),
                StatusConciliacao.PENDENTE);

        TransacaoInterna tInterna2 = new TransacaoInterna(
                UUID.randomUUID(), "Venda",
                new BigDecimal("200.00"),
                LocalDateTime.now(),
                StatusConciliacao.CONCILIADO);

        TransacaoInterna tInterna3 = new TransacaoInterna(
                UUID.randomUUID(), "Venda",
                new BigDecimal("300.00"),
                LocalDateTime.now(),
                StatusConciliacao.PENDENTE);

        transacaoInternaRepositoryImpl.salvar(tInterna);
        transacaoInternaRepositoryImpl.salvar(tInterna2);
        transacaoInternaRepositoryImpl.salvar(tInterna3);
        Stream<TransacaoInterna> TransacaoStatus = transacaoInternaRepositoryImpl.buscaPorStatus(StatusConciliacao.PENDENTE);

        assertThat(TransacaoStatus)
                .hasSize(2)
                .extracting(TransacaoInterna::status)
                .containsOnly(StatusConciliacao.PENDENTE);

    }
}
