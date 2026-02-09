package com.example.conciliamento_gerenciamento.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record Divergente(
        UUID idTransacaoInterna,
        BigDecimal valorEsperado,
        BigDecimal valorRecebido,
        BigDecimal valorDiferenca
) implements ResultadoConciliacao{
}
