package com.example.conciliamento_gerenciamento.infrastructure.entity;


import com.example.conciliamento_gerenciamento.domain.model.StatusConciliacao;
import com.example.conciliamento_gerenciamento.domain.model.TransacaoInterna;
import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table (name = "transacoes_internas")
public class TransacaoInternaEntity {

    @Id
    private UUID idTransacaoInterna;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valorPrevisto;

    @Column(nullable = false)
    private LocalDateTime dataTransacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusConciliacao status;

    protected TransacaoInternaEntity() {}

    public TransacaoInternaEntity (TransacaoInterna transacao){
        this.idTransacaoInterna = transacao.idTransacaoInterna();
        this.descricao = transacao.descricao();
        this.valorPrevisto = transacao.valorPrevisto();
        this.dataTransacao = transacao.dataTransacao();
        this.status = transacao.status();
    }

    public TransacaoInterna toDomain(){
        return new TransacaoInterna(idTransacaoInterna, descricao,valorPrevisto, dataTransacao, status);
    }
}
