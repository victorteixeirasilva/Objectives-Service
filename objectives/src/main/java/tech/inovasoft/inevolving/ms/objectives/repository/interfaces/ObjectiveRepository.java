package tech.inovasoft.inevolving.ms.objectives.repository.interfaces;

import tech.inovasoft.inevolving.ms.objectives.domain.model.Objective;

public interface ObjectiveRepository {

    Objective save(Objective newObjective);
}
