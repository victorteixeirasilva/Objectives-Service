package tech.inovasoft.inevolving.ms.objectives.repository.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tech.inovasoft.inevolving.ms.objectives.domain.exception.DataBaseException;
import tech.inovasoft.inevolving.ms.objectives.domain.exception.NotFoundObjectivesByUser;
import tech.inovasoft.inevolving.ms.objectives.domain.model.Objective;
import tech.inovasoft.inevolving.ms.objectives.repository.interfaces.ObjectiveJpaRepository;
import tech.inovasoft.inevolving.ms.objectives.repository.interfaces.ObjectiveRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ObjectiveRepositoryImplementation implements ObjectiveRepository {

    @Autowired
    private ObjectiveJpaRepository objectiveJpaRepository;

    /**
     * @description - Saves the new goal to the database. | Salva o novo objetivo no banco de dados.
     * @param newObjective - The new objective to be saved. | O novo objetivo a ser salvo.
     * @return - The saved objective. | O objetivo salvo.
     * @throws DataBaseException - If there is an error saving the objective to the database. | Se houver algum erro ao salvar o objetivo no banco de dados.
     */
    @Override
    public Objective save(Objective newObjective) throws DataBaseException {
        try {
            return objectiveJpaRepository.save(newObjective);
        } catch (Exception e) {
            throw new DataBaseException("(save)");
        }
    }

    /**
     * @description - Finds the objective by id and idUser. | Encontra o objetivo pelo id e idUser.
     * @param idObjective - The id of the objective to be found. | O id do objetivo a ser encontrado.
     * @param idUser - The id of the user who owns the objective. | O id do usuário que possui o objetivo.
     * @return - The found objective. | O objetivo encontrado.
     * @throws DataBaseException - If there is an error finding the objective. | Se houver algum erro ao encontrar o objetivo.
     * @throws NotFoundObjectivesByUser - If the objective is not found. | Se o objetivo não for encontrado.
     */
    @Override
    public Objective findByIdAndIdUser(
            UUID idObjective,
            UUID idUser
    ) throws DataBaseException, NotFoundObjectivesByUser {
        Optional<Objective> objective;
        try {
            objective = objectiveJpaRepository.findByIdAndIdUser(idObjective, idUser);
        } catch (Exception e) {
            throw new DataBaseException("(findByIdAndIdUser)");
        }

        if (objective.isEmpty()) {
            throw new NotFoundObjectivesByUser();
        }

        return objective.get();
    }

    /**
     * @description - Finds all objectives by idUser. | Encontra todos os objetivos pelo idUser.
     * @param idUser - The id of the user who owns the objectives. | O id do usuário que possui os objetivos.
     * @return - A list of objectives. | Uma lista de objetivos.
     * @throws DataBaseException - If there is an error finding the objectives. | Se houver algum erro ao encontrar os objetivos.
     */
    @Override
    public List<Objective> findAllByIdUser(
            UUID idUser
    ) throws DataBaseException {
        try {
            return objectiveJpaRepository.findAllByIdUser(idUser);
        } catch (Exception e) {
            throw new DataBaseException("(findAllByIdUser)");
        }
    }

    /**
     * @description - Finds all objectives by idUser and status. | Encontra todos os objetivos pelo idUser e status.
     * @param idUser - The id of the user who owns the objectives. | O id do usuário que possui os objetivos.
     * @param status - The status of the objectives. | O status dos objetivos.
     * @return - A list of objectives. | Uma lista de objetivos.
     * @throws DataBaseException - If there is an error finding the objectives. | Se houver algum erro ao encontrar os objetivos.
     */
    @Override
    public List<Objective> findAllByIdUserAndStatus(
            UUID idUser,
            String status
    ) throws DataBaseException {
        try {
            return objectiveJpaRepository.findAllByIdUserAndStatus(idUser, status);
        } catch (Exception e) {
            throw new DataBaseException("(findAllByIdUserAndStatus)");
        }
    }
}
