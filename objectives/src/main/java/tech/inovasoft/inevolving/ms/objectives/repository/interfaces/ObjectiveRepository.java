package tech.inovasoft.inevolving.ms.objectives.repository.interfaces;

import tech.inovasoft.inevolving.ms.objectives.domain.model.Objective;

import java.util.List;
import java.util.UUID;

public interface ObjectiveRepository {

    Objective save(Objective newObjective);

    Objective findByIdAndIdUser(UUID idObjective, UUID idUser);

    List<Objective> findAllByIdUser(UUID idUser);

    List<Objective> findAllByIdUserAndStatus(UUID idUser, String todo);
}
