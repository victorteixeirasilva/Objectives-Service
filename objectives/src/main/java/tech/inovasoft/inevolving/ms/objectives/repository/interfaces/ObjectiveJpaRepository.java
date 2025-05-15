package tech.inovasoft.inevolving.ms.objectives.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.inovasoft.inevolving.ms.objectives.domain.model.Objective;

import java.util.UUID;

public interface ObjectiveJpaRepository extends JpaRepository<Objective, UUID> {
}
