package tech.inovasoft.inevolving.ms.objectives.repository.interfaces;

import tech.inovasoft.inevolving.ms.objectives.domain.model.Objective;

import java.util.UUID;

public interface ObjectiveRepository {

    Objective save(Objective newObjective);

    Objective findById(UUID idObjective);

    Objective findByIdAndIdUser(UUID idObjective, UUID idUser);
}
