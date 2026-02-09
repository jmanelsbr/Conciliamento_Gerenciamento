package com.example.conciliamento_gerenciamento;

import org.springframework.boot.SpringApplication;

public class TestConciliamentoGerenciamentoApplication {

    public static void main(String[] args) {
        SpringApplication.from(ConciliamentoGerenciamentoApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
