package tech.inovasoft.inevolving.ms.objectives.unit.success;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import tech.inovasoft.inevolving.ms.objectives.domain.dto.request.RequestCreateObjectiveDTO;
import tech.inovasoft.inevolving.ms.objectives.domain.model.Objective;
import tech.inovasoft.inevolving.ms.objectives.repository.interfaces.ObjectiveRepository;
import tech.inovasoft.inevolving.ms.objectives.service.ObjectivesService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ObjectiveServiceSuccess {

    @Mock
    private ObjectiveRepository repository;

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

        Objective newObjective = new Objective(request);

        Objective expected = newObjective;
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



}
