package tech.inovasoft.inevolving.ms.objectives.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.inovasoft.inevolving.ms.objectives.domain.model.Objective;

import java.util.Optional;
import java.util.UUID;

public interface ObjectiveJpaRepository extends JpaRepository<Objective, UUID> {

    @Query("") // TODO: Criar Query JPQL
    Optional<Objective> findByIdAndIdUser(UUID idObjective, UUID idUser);



}
