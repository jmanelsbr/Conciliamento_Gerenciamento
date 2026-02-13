package com.example.conciliamento_gerenciamento.infrastructure.repository;

import com.example.conciliamento_gerenciamento.domain.model.StatusConciliacao;
import com.example.conciliamento_gerenciamento.infrastructure.entity.TransacaoInternaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface TransacaoInternaDAO extends JpaRepository<TransacaoInternaEntity, UUID> {

    List<TransacaoInternaEntity> findByStatus(StatusConciliacao status);

}
