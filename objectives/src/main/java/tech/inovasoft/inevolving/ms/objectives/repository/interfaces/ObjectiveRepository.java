package tech.inovasoft.inevolving.ms.objectives.repository.interfaces;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tech.inovasoft.inevolving.ms.objectives.domain.exception.DataBaseException;
import tech.inovasoft.inevolving.ms.objectives.domain.exception.NotFoundObjectivesByUser;
import tech.inovasoft.inevolving.ms.objectives.domain.model.Objective;

import java.util.List;
import java.util.UUID;

@Service
public interface ObjectiveRepository {

    Objective save(Objective newObjective) throws DataBaseException;

    Objective findByIdAndIdUser(UUID idObjective, UUID idUser) throws DataBaseException, NotFoundObjectivesByUser;

    List<Objective> findAllByIdUser(UUID idUser) throws DataBaseException;

    List<Objective> findAllByIdUserAndStatus(UUID idUser, String todo) throws DataBaseException;
}
