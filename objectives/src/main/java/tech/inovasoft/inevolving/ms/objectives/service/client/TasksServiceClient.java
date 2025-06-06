package tech.inovasoft.inevolving.ms.objectives.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@FeignClient(name = "tasks-service", url = "http://localhost:8081/ms/tasks") //TODO: Mudar para o endereço do container e esconder ele em variaveis de ambiente
public interface TasksServiceClient {

    // TODO: Fazer teste de integração.
    @DeleteMapping("/lock/{completionDate}/{idUser}/{idObjective}")
    ResponseEntity lockTaskByObjective(
            // TODO: Desenvolver DTO dos dados recebidos, e preencher o ResponseEntity
            @PathVariable Date completionDate,
            @PathVariable UUID idUser,
            @PathVariable UUID idObjective
    );

}
