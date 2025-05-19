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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
}
