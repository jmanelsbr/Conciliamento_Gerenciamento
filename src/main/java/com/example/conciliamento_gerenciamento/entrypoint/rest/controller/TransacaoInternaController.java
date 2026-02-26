package com.example.conciliamento_gerenciamento.entrypoint.rest.controller;

import com.example.conciliamento_gerenciamento.domain.model.TransacaoInterna;
import com.example.conciliamento_gerenciamento.domain.repository.TransacaoInternaRepository;
import com.example.conciliamento_gerenciamento.entrypoint.rest.dto.TransacaoInternaRequest;
import com.example.conciliamento_gerenciamento.entrypoint.rest.dto.TransacaoInternaResponse;
import com.example.conciliamento_gerenciamento.entrypoint.rest.mapper.TransacaoInternaMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("/{id}")
    ResponseEntity<TransacaoInternaResponse> get (@PathVariable UUID id){
        return transacaoInternaRepository.buscaPorId(id)
                .map(transacaoInternaMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    ResponseEntity<TransacaoInternaResponse> put (@PathVariable UUID id, @RequestBody TransacaoInternaRequest transacaoInternaRequest){

        Optional<TransacaoInterna> transacaoExistente = transacaoInternaRepository.buscaPorId(id);

        if (transacaoExistente.isEmpty()){
                        return ResponseEntity.notFound().build();
                    }
        var transacaoNova = new TransacaoInterna(
                           transacaoExistente.get().idTransacaoInterna(),
                           transacaoInternaRequest.descricao(),
                           transacaoInternaRequest.valorPrevisto(),
                           transacaoInternaRequest.dataTransacao(),
                           transacaoExistente.get().status());
        transacaoInternaRepository.salvar(transacaoNova);
        return ResponseEntity.ok()
                .body(transacaoInternaMapper.toResponse(transacaoNova));


               }

    //Delete Provisório
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete (@PathVariable UUID id){
        Optional<TransacaoInterna> transacaoExistente = transacaoInternaRepository.buscaPorId(id);

        if (transacaoExistente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        transacaoInternaRepository.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    }

