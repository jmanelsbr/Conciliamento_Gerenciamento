package com.example.conciliamento_gerenciamento.domain.model;

import com.example.conciliamento_gerenciamento.domain.exception.ValidacaoDominioException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransacaoInternaTest {

    @Test
    void sucessoAoCriarTransacaoInterna(){
        var idInterno = UUID.randomUUID();

        assertDoesNotThrow(() -> {
            var tInterna = new TransacaoInterna(
                    idInterno, "Venda",
                    new BigDecimal("100.00"),
                    LocalDateTime.now(),
                    StatusConciliacao.PENDENTE);
            }
        );
    }

    @Test
    void falhaAoCriarTransacaoInternaComValorNegativo(){
        var idInterno = UUID.randomUUID();

        ValidacaoDominioException exception = assertThrows(ValidacaoDominioException.class, () -> {
                    var tInterna = new TransacaoInterna(
                            idInterno, "Venda",
                            new BigDecimal("-100.00"),
                            LocalDateTime.now(),
                            StatusConciliacao.PENDENTE);
                });
        assertThat(exception.getMessage()).isEqualTo("O valor previsto deve ser positivo e não nulo");

    }
}
