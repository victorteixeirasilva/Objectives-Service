package tech.inovasoft.inevolving.ms.objectives.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.inovasoft.inevolving.ms.objectives.domain.model.Objective;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ObjectiveJpaRepository extends JpaRepository<Objective, UUID> {

    @Query("SELECT o FROM Objective o WHERE o.id = :idObjective AND o.idUser = :idUser")
    Optional<Objective> findByIdAndIdUser(
            @Param("idObjective") UUID idObjective,
            @Param("idUser") UUID idUser
    );

    @Query("SELECT o FROM Objective o WHERE o.idUser = :idUser")
    List<Objective> findAllByIdUser(
            @Param("idUser") UUID idUser
    );

    @Query("SELECT o FROM Objective o WHERE o.idUser = :idUser AND o.statusObjective = :status")
    List<Objective> findAllByIdUserAndStatus(
            @Param("idUser") UUID idUser,
            @Param("status") String status
    );
}
