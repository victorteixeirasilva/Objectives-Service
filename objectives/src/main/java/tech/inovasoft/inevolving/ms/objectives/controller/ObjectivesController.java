package tech.inovasoft.inevolving.ms.objectives.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import tech.inovasoft.inevolving.ms.objectives.domain.dto.request.RequestCreateObjectiveDTO;
import tech.inovasoft.inevolving.ms.objectives.service.ObjectivesService;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Tag(name = "Objectives | Objetivos", description = "Manager of Objectives | Gerenciador dos end-points de Objetivos")
@RestController
@RequestMapping("/ms/objectives")
public class ObjectivesController {

    @Autowired
    private ObjectivesService objectivesService;

    @Operation(
            summary = "Add a new objective | Adicionar um novo objetivo",
            description = "Returns the registered objective. | Retorna o objetivo cadastrado."
    )
    @Async("asyncExecutor")
    @PostMapping
    public CompletableFuture<ResponseEntity> add(@RequestBody RequestCreateObjectiveDTO dto) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(objectivesService.addObjective(dto)));
    }

    @Operation(
            summary = "Update Objective. | Atualizar Objetivo.",
            description = "Returns the updated objective. | Retorna o objetivo atualizado."
    )
    @Async("asyncExecutor")
    @PutMapping("/{idObjective}")
    public CompletableFuture<ResponseEntity> update(@PathVariable UUID idObjective, @RequestBody RequestCreateObjectiveDTO dto) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(objectivesService.updateObjective(idObjective, dto)));
    }

    @Operation(
            summary = "Complete Objective. | Concluir Objetivo.",
            description = "Returns confirmation that the objective has been completed. | Retorna a confirmação de que o objetivo foi concluido."
    )
    @Async("asyncExecutor")
    @PatchMapping("/{idObjective}/{conclusionDate}")
    public CompletableFuture<ResponseEntity> completeObjective(@PathVariable UUID idObjective, @PathVariable LocalDate conclusionDate) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(objectivesService.completeObjective(idObjective, conclusionDate)));
    }

}
