package com.example.conciliamento_gerenciamento.domain.service;

import com.example.conciliamento_gerenciamento.domain.model.StatusConciliacao;
import com.example.conciliamento_gerenciamento.domain.model.TransacaoBancaria;
import com.example.conciliamento_gerenciamento.domain.model.TransacaoInterna;
import com.example.conciliamento_gerenciamento.domain.repository.TransacaoInternaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConciliacaoServiceTest {

    @Mock
    private TransacaoInternaRepository repository;

    @InjectMocks
    private ConciliacaoService service;

    @Test
    void processarConciliacao_ComValoresIguais_DeveSalvarComoConciliado() {

        var idInterno = UUID.randomUUID();
        var tInterna = new TransacaoInterna(
                idInterno, "Venda",
                new BigDecimal("100.00"),
                LocalDateTime.now(),
                StatusConciliacao.PENDENTE
        );

        var tBancaria = new TransacaoBancaria(
                UUID.randomUUID().toString(),
                "Depósito Identificado",
                new BigDecimal("100.00"),
                LocalDateTime.now()
        );

        when(repository.buscaPorStatus(StatusConciliacao.PENDENTE))
                .thenReturn(Stream.of(tInterna));

        service.processarConciliacao(tBancaria);

        ArgumentCaptor<TransacaoInterna> captor = ArgumentCaptor.forClass(TransacaoInterna.class);
        verify(repository, times(1)).salvar(captor.capture());

        TransacaoInterna transacaoSalva = captor.getValue();

        assertEquals(StatusConciliacao.CONCILIADO, transacaoSalva.status());
        assertEquals(idInterno, transacaoSalva.idTransacaoInterna());
    }
}