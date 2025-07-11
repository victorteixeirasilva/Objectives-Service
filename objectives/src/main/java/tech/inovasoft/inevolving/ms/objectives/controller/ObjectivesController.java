package tech.inovasoft.inevolving.ms.objectives.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import tech.inovasoft.inevolving.ms.objectives.domain.dto.request.RequestCreateObjectiveDTO;
import tech.inovasoft.inevolving.ms.objectives.domain.dto.response.ResponseMessageDTO;
import tech.inovasoft.inevolving.ms.objectives.domain.exception.DataBaseException;
import tech.inovasoft.inevolving.ms.objectives.domain.exception.InternalErrorException;
import tech.inovasoft.inevolving.ms.objectives.domain.exception.NotFoundObjectivesByUser;
import tech.inovasoft.inevolving.ms.objectives.domain.exception.NotFoundObjectivesByUserAndStatus;
import tech.inovasoft.inevolving.ms.objectives.domain.model.Objective;
import tech.inovasoft.inevolving.ms.objectives.domain.model.Status;
import tech.inovasoft.inevolving.ms.objectives.service.ObjectivesService;

import java.time.LocalDate;
import java.util.List;
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
    public CompletableFuture<ResponseEntity<Objective>> add(
            @RequestBody RequestCreateObjectiveDTO dto
    ) throws DataBaseException {
        return CompletableFuture.completedFuture(ResponseEntity.ok(
                objectivesService.addObjective(dto)
        ));
    }

    @Operation(
            summary = "Update Objective. | Atualizar Objetivo.",
            description = "Returns the updated objective. | Retorna o objetivo atualizado."
    )
    @Async("asyncExecutor")
    @PutMapping("/{idObjective}/{idUser}")
    public CompletableFuture<ResponseEntity<Objective>> update(
            @PathVariable UUID idObjective,
            @PathVariable UUID idUser,
            @RequestBody RequestCreateObjectiveDTO dto
    ) throws DataBaseException, NotFoundObjectivesByUser {
        return CompletableFuture.completedFuture(ResponseEntity.ok(
                        objectivesService.updateObjective(idObjective, dto, idUser)
        ));
    }

    @Operation(
            summary = "Complete Objective. | Concluir Objetivo.",
            description = "Returns confirmation that the objective has been completed. | Retorna a confirmação de que o objetivo foi concluido."
    )
    @Async("asyncExecutor")
    @PutMapping("/{idObjective}/{conclusionDate}/{idUser}")
    public CompletableFuture<ResponseEntity<ResponseMessageDTO>> completeObjective(
            @PathVariable UUID idObjective,
            @PathVariable LocalDate conclusionDate,
            @PathVariable UUID idUser
    ) throws InternalErrorException, DataBaseException, NotFoundObjectivesByUser {
        return CompletableFuture.completedFuture(ResponseEntity.ok(
                        objectivesService.completeObjective(idObjective, conclusionDate, idUser)
        ));
    }

    @Operation(
            summary = "Get target by id | Pegar objetivo por id.",
            description = "Returns the found target. | Retorna o objetivo encontrado."
    )
    @Async("asyncExecutor")
    @GetMapping("/{idObjective}/{idUser}")
    public CompletableFuture<ResponseEntity<Objective>> getObjectiveById(
            @PathVariable UUID idObjective,
            @PathVariable UUID idUser
    ) throws DataBaseException, NotFoundObjectivesByUser {
        return CompletableFuture.completedFuture(ResponseEntity.ok(
                        objectivesService.getObjectiveById(idObjective, idUser)
        ));
    }

    @Operation(
            summary = "Get all user objectives. | Pegar todos os objetivos do usuário.",
            description = "Returns a list of all the user's goals. | Retorna uma lista com todos os objetivos do usuário."
    )
    @Async("asyncExecutor")
    @GetMapping("/user/{idUser}")
    public CompletableFuture<ResponseEntity<List<Objective>>>  getObjectivesByIdUser(
            @PathVariable UUID idUser
    ) throws NotFoundObjectivesByUser, DataBaseException {
        return CompletableFuture.completedFuture(ResponseEntity.ok(
                        objectivesService.getObjectivesByIdUser(idUser)
        ));
    }

    @Operation(
            summary = "Get all user goals that have not yet been completed. | Pegar todos os objetivos do usuário que ainda não foram concluídos.",
            description = "Returns a list of all the user's uncompleted goals. | Retorna uma lista com todos os objetivos não concluídos do usuário."
    )
    @Async("asyncExecutor")
    @GetMapping("/status/todo/user/{idUser}")
    public CompletableFuture<ResponseEntity<List<Objective>>> getObjectivesByIdUserToDo(
            @PathVariable UUID idUser
    ) throws NotFoundObjectivesByUserAndStatus, DataBaseException {
        return CompletableFuture.completedFuture(ResponseEntity.ok(
                        objectivesService.getObjectivesByIdUserStatus(idUser, Status.TODO)
        ));
    }

    @Operation(
            summary = "Get all user goals that have been completed. | Pegar todos os objetivos do usuário que foram concluídos.",
            description = "Returns a list of all completed goals for the user. | Retorna uma lista com todos os objetivos concluídos do usuário."
    )
    @Async("asyncExecutor")
    @GetMapping("/status/done/user/{idUser}")
    public CompletableFuture<ResponseEntity<List<Objective>>> getObjectivesByIdUserDone(
            @PathVariable UUID idUser
    ) throws NotFoundObjectivesByUserAndStatus, DataBaseException {
        return CompletableFuture.completedFuture(ResponseEntity.ok(
                        objectivesService.getObjectivesByIdUserStatus(idUser, Status.DONE)
        ));
    }

}
