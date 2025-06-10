package tech.inovasoft.inevolving.ms.objectives.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.inovasoft.inevolving.ms.objectives.domain.dto.response.ResponseMessageDTO;

import java.sql.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@FeignClient(name = "tasks-service", url = "http://localhost:8081/ms/tasks")
public interface TasksServiceClient {

    @DeleteMapping("/lock/{completionDate}/{idUser}/{idObjective}")
    ResponseEntity<ResponseMessageDTO> lockTaskByObjective(
            @PathVariable Date completionDate,
            @PathVariable UUID idUser,
            @PathVariable UUID idObjective
    );

}
