package com.example.conciliamento_gerenciamento.infrastructure.repository;

import com.example.conciliamento_gerenciamento.domain.model.StatusConciliacao;
import com.example.conciliamento_gerenciamento.domain.model.TransacaoInterna;
import com.example.conciliamento_gerenciamento.domain.repository.TransacaoInternaRepository;
import com.example.conciliamento_gerenciamento.infrastructure.entity.TransacaoInternaEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public class TransacaoInternaRepositoryImpl implements TransacaoInternaRepository {
    final private TransacaoInternaDAO dao;

    public TransacaoInternaRepositoryImpl(TransacaoInternaDAO dao) {
        this.dao = dao;
    }


    @Override
    public Optional<TransacaoInterna> buscaPorId(UUID id) {
        return dao.findById(id).map(TransacaoInternaEntity::toDomain);
    }

    @Override
    public Stream<TransacaoInterna> buscaPorStatus(StatusConciliacao status) {
        return dao.findByStatus(status).stream().map(TransacaoInternaEntity::toDomain);
    }

    @Override
    public TransacaoInterna salvar(TransacaoInterna transacao) {
        return dao.save(new TransacaoInternaEntity(transacao)).toDomain();
    }
}
