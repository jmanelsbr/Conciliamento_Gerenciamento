package com.example.conciliamento_gerenciamento.infrastructure.config;

import com.example.conciliamento_gerenciamento.domain.repository.TransacaoInternaRepository;
import com.example.conciliamento_gerenciamento.domain.service.ConciliacaoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
     public ConciliacaoService conciliacaoServiceBean (TransacaoInternaRepository transacaoInternaRepository){
        return new ConciliacaoService(transacaoInternaRepository);
    }
}
