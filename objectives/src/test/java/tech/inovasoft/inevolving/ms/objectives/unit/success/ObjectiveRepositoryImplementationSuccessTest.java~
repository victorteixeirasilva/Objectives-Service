package tech.inovasoft.inevolving.ms.objectives.unit.success;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.inovasoft.inevolving.ms.objectives.domain.dto.request.RequestCreateObjectiveDTO;
import tech.inovasoft.inevolving.ms.objectives.domain.exception.DataBaseException;
import tech.inovasoft.inevolving.ms.objectives.domain.model.Objective;
import tech.inovasoft.inevolving.ms.objectives.repository.implementation.ObjectiveRepositoryImplementation;
import tech.inovasoft.inevolving.ms.objectives.repository.interfaces.ObjectiveJpaRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ObjectiveRepositoryImplementationSuccessTest {

    @Mock
    private ObjectiveJpaRepository objectiveJpaRepository;

    @InjectMocks
    private ObjectiveRepositoryImplementation objectiveRepositoryImplementation;

    @Test
    public void save() throws DataBaseException {
        //Given
        RequestCreateObjectiveDTO request = new RequestCreateObjectiveDTO(
                "Objetivo 1",
                "Descrição 1",
                UUID.randomUUID()
        );

        Objective expected = new Objective(request);
        expected.setId(UUID.randomUUID());

        //When
        when(objectiveJpaRepository.save(expected)).thenReturn(expected);
        var result = objectiveRepositoryImplementation.save(expected);

        //Then
        assertNotNull(result);
        verify(objectiveJpaRepository).save(expected);
    }

    @Test
    public void findByIdAndIdUser() {
        //Given
        var idObjective = UUID.randomUUID();
        var idUser = UUID.randomUUID();
        var objective = new Objective();
        objective.setId(idObjective);
        objective.setNameObjective("Objetivo");
        objective.setDescriptionObjective("Objetivo");
        objective.setIdUser(idUser);

        //When
        when(objectiveJpaRepository.findByIdAndIdUser(idObjective, idUser)).thenReturn(objective);
        var result = objectiveRepositoryImplementation.findByIdAndIdUser(idObjective, idUser);

        //Then
        assertNotNull(result);
        assertEquals(objective.getId(), result.getId());
        assertEquals(objective.getNameObjective(), result.getNameObjective());
        assertEquals(objective.getDescriptionObjective(), result.getDescriptionObjective());
        assertEquals(objective.getIdUser(), result.getIdUser());

        verify(objectiveJpaRepository, times(1)).findByIdAndIdUser(idObjective, idUser);
    }
}
