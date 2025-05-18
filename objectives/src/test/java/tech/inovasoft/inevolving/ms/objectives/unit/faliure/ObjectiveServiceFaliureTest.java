package tech.inovasoft.inevolving.ms.objectives.unit.faliure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import tech.inovasoft.inevolving.ms.objectives.domain.dto.response.ResponseMessageDTO;
import tech.inovasoft.inevolving.ms.objectives.domain.exception.InternalErrorException;
import tech.inovasoft.inevolving.ms.objectives.domain.model.Objective;
import tech.inovasoft.inevolving.ms.objectives.repository.interfaces.ObjectiveRepository;
import tech.inovasoft.inevolving.ms.objectives.service.ObjectivesService;
import tech.inovasoft.inevolving.ms.objectives.service.client.TasksServiceClient;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ObjectiveServiceFaliureTest {
    @Mock
    private ObjectiveRepository repository;

    @Mock
    private TasksServiceClient tasksServiceClient;

    @InjectMocks
    private ObjectivesService service;

    @Test
    public void completeObjectiveInternalErrorException() throws InternalErrorException {
        //Given
        var idObjective = UUID.randomUUID();
        LocalDate date = LocalDate.now();
        var idUser = UUID.randomUUID();

        var objective = new Objective();
        objective.setId(idObjective);
        objective.setNameObjective("Objetivo");
        objective.setDescriptionObjective("Objetivo");
        objective.setIdUser(idUser);

        //When
        when(repository.findByIdAndIdUser(idObjective, idUser)).thenReturn(objective);
        when(tasksServiceClient.lockTaskByObjective(Date.valueOf(date), idUser, idObjective))
                .thenReturn(ResponseEntity.badRequest().build());
        var result = assertThrows(InternalErrorException.class, () -> {
            service.completeObjective(idObjective, date, idUser);
        });

        //Then
        assertNotNull(result);
        assertEquals("Error in lock tasks by objective!", result.getMessage());

        verify(repository, times(1)).findByIdAndIdUser(idObjective, idUser);
        verify(tasksServiceClient, times(1)).lockTaskByObjective(Date.valueOf(date), idUser, idObjective);
    }
}
