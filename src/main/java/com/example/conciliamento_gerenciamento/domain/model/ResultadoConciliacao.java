package com.example.conciliamento_gerenciamento.domain.model;

import java.util.UUID;

public sealed interface ResultadoConciliacao permits Conciliado, Divergente, NaoEncontrado{

    UUID idTransacaoInterna();
}
