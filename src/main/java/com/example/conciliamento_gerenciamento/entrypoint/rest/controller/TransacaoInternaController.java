package com.example.conciliamento_gerenciamento.entrypoint.rest.controller;

import com.example.conciliamento_gerenciamento.domain.repository.TransacaoInternaRepository;
import com.example.conciliamento_gerenciamento.entrypoint.rest.dto.TransacaoInternaRequest;
import com.example.conciliamento_gerenciamento.entrypoint.rest.dto.TransacaoInternaResponse;
import com.example.conciliamento_gerenciamento.entrypoint.rest.mapper.TransacaoInternaMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoInternaController {

    private final TransacaoInternaMapper transacaoInternaMapper;

    private final TransacaoInternaRepository transacaoInternaRepository;


    public TransacaoInternaController(TransacaoInternaMapper transacaoInternaMapper, TransacaoInternaRepository transacaoInternaRepository) {
        this.transacaoInternaMapper = transacaoInternaMapper;
        this.transacaoInternaRepository = transacaoInternaRepository;
    }

    @PostMapping
    ResponseEntity<TransacaoInternaResponse> post (@RequestBody TransacaoInternaRequest transacaoInternaRequest){
        var requestToEntity = transacaoInternaMapper.toEntity(transacaoInternaRequest);
        transacaoInternaRepository.salvar(requestToEntity);
        var entityToResponse = transacaoInternaMapper.toResponse(requestToEntity);
        return ResponseEntity.status(CREATED).body(entityToResponse);
    }

}
