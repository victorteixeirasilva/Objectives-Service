package tech.inovasoft.inevolving.ms.objectives.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import tech.inovasoft.inevolving.ms.objectives.domain.dto.request.RequestCreateObjectiveDTO;

import java.util.concurrent.CompletableFuture;

@Tag(name = "Objectives | Objetivos", description = "Manager of Objectives | Gerenciador dos end-points de Objetivos")
@RestController
@RequestMapping("/ms/objectives")
public class ObjectivesController {

    @Operation(
            summary = "Add a new objective | Adicionar um novo objetivo",
            description = "Returns the registered objective. | Retorna o objetivo cadastrado."
    )
    @Async("asyncExecutor")
    @PostMapping
    public CompletableFuture<ResponseEntity> add(@RequestBody RequestCreateObjectiveDTO dto) {
        // TODO: Criar Método do service
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Operation(
            summary = "Update Objective. | Atualizar Objetivo.",
            description = "Returns the updated objective. | Retorna o objetivo atualizado."
    )
    @Async("asyncExecutor")
    @PutMapping
    public CompletableFuture<ResponseEntity> update(@RequestBody RequestCreateObjectiveDTO dto) {
        // TODO: Criar Método do service
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }



}
