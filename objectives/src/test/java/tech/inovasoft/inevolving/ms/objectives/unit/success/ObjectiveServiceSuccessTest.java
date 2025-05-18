package tech.inovasoft.inevolving.ms.objectives.unit.success;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import tech.inovasoft.inevolving.ms.objectives.domain.dto.request.RequestCreateObjectiveDTO;
import tech.inovasoft.inevolving.ms.objectives.domain.dto.response.ResponseMessageDTO;
import tech.inovasoft.inevolving.ms.objectives.domain.exception.InternalErrorException;
import tech.inovasoft.inevolving.ms.objectives.domain.exception.NotFoundObjectivesByUser;
import tech.inovasoft.inevolving.ms.objectives.domain.exception.NotFoundObjectivesByUserAndStatus;
import tech.inovasoft.inevolving.ms.objectives.domain.model.Objective;
import tech.inovasoft.inevolving.ms.objectives.domain.model.Status;
import tech.inovasoft.inevolving.ms.objectives.repository.interfaces.ObjectiveRepository;
import tech.inovasoft.inevolving.ms.objectives.service.ObjectivesService;
import tech.inovasoft.inevolving.ms.objectives.service.client.TasksServiceClient;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ObjectiveServiceSuccessTest {

    @Mock
    private ObjectiveRepository repository;

    @Mock
    private TasksServiceClient tasksServiceClient;

    @InjectMocks
    private ObjectivesService service;

    @Test
    public void addObjective(){
        //Given
        RequestCreateObjectiveDTO request = new RequestCreateObjectiveDTO(
                "Objetivo 1",
                "Descrição 1",
                UUID.randomUUID()
        );

        Objective expected = new Objective(request);
        expected.setId(UUID.randomUUID());

        //When
        when(repository.save(any())).thenReturn(expected);
        var result = service.addObjective(request);

        //Then
        assertNotNull(result);
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getNameObjective(), result.getNameObjective());
        assertEquals(expected.getDescriptionObjective(), result.getDescriptionObjective());
        assertEquals(expected.getIdUser(), result.getIdUser());

        verify(repository, times(1)).save(any());
    }

    @Test
    public void updateObjective(){
        //Given
        var idObjective = UUID.randomUUID();
        RequestCreateObjectiveDTO request = new RequestCreateObjectiveDTO(
                "Objetivo 1",
                "Descrição 1",
                UUID.randomUUID()
        );
        var oldObjective = new Objective();
        oldObjective.setId(idObjective);
        oldObjective.setNameObjective("Antigo");
        oldObjective.setDescriptionObjective("Antigo");
        oldObjective.setIdUser(request.idUser());

        var newObjective = new Objective(request);
        newObjective.setId(idObjective);
        newObjective.setNameObjective(request.nameObjective());
        newObjective.setDescriptionObjective(request.descriptionObjective());
        newObjective.setIdUser(request.idUser());

        //When
        when(repository.findByIdAndIdUser(idObjective, request.idUser())).thenReturn(oldObjective);
        when(repository.save(any())).thenReturn(newObjective);
        var result = service.updateObjective(idObjective, request, request.idUser());

        //Then
        assertNotNull(result);
        assertEquals(newObjective.getId(), result.getId());
        assertEquals(newObjective.getNameObjective(), result.getNameObjective());
        assertEquals(newObjective.getDescriptionObjective(), result.getDescriptionObjective());
        assertEquals(newObjective.getIdUser(), result.getIdUser());

        verify(repository, times(1)).findByIdAndIdUser(idObjective, request.idUser());
        verify(repository, times(1)).save(any());
    }

    @Test
    public void completeObjective() throws InternalErrorException {
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
                .thenReturn(ResponseEntity.ok().build());
        when(repository.save(any())).thenReturn(objective);
        ResponseMessageDTO result = service.completeObjective(idObjective, date, idUser);

        //Then
        assertNotNull(result);
        assertEquals("Objective successfully completed", result.message());

        verify(repository, times(1)).findByIdAndIdUser(idObjective, idUser);
        verify(tasksServiceClient, times(1)).lockTaskByObjective(Date.valueOf(date), idUser, idObjective);

    }

    @Test
    public void getObjectiveById() {
        //Given
        var idObjective = UUID.randomUUID();
        var idUser = UUID.randomUUID();
        var objective = new Objective();
        objective.setId(idObjective);
        objective.setNameObjective("Objetivo");
        objective.setDescriptionObjective("Objetivo");
        objective.setIdUser(idUser);

        //When
        when(repository.findByIdAndIdUser(idObjective, idUser)).thenReturn(objective);
        var result = service.getObjectiveById(idObjective, idUser);

        //Then
        assertNotNull(result);
        assertEquals(objective.getId(), result.getId());
        assertEquals(objective.getNameObjective(), result.getNameObjective());
        assertEquals(objective.getDescriptionObjective(), result.getDescriptionObjective());
        assertEquals(objective.getIdUser(), result.getIdUser());

        verify(repository, times(1)).findByIdAndIdUser(idObjective, idUser);
    }

    @Test
    public void getObjectivesByIdUser() throws NotFoundObjectivesByUser {
        //Given
        var idUser = UUID.randomUUID();
        List<Objective> objectives = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            objectives.add(new Objective(
                    UUID.randomUUID(),
                    "Objetivo " + i,
                    "Descrição " + i,
                    Status.TODO,
                    null,
                    idUser
            ));
        }

        //When
        when(repository.findAllByIdUser(idUser)).thenReturn(objectives);
        var result = service.getObjectivesByIdUser(idUser);

        //Then
        assertNotNull(result);
        assertEquals(objectives.size(), result.size());

        verify(repository, times(1)).findAllByIdUser(idUser);
    }

    @Test
    public void getObjectivesByIdUserStatus() throws NotFoundObjectivesByUserAndStatus {
        //Given
        var idUser = UUID.randomUUID();
        List<Objective> objectives = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            objectives.add(new Objective(
                    UUID.randomUUID(),
                    "Objetivo " + i,
                    "Descrição " + i,
                    Status.TODO,
                    null,
                    idUser
            ));
        }

        //When
        when(repository.findAllByIdUserAndStatus(idUser, Status.TODO)).thenReturn(objectives);
        var result = service.getObjectivesByIdUserStatus(idUser, Status.TODO);

        //Then
        assertNotNull(result);
        assertEquals(objectives.size(), result.size());
        assertEquals(Status.TODO, result.getFirst().getStatusObjective());

        verify(repository, times(1)).findAllByIdUserAndStatus(idUser, Status.TODO);

    }



}
