package tech.inovasoft.inevolving.ms.objectives.repository.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import tech.inovasoft.inevolving.ms.objectives.domain.exception.DataBaseException;
import tech.inovasoft.inevolving.ms.objectives.domain.model.Objective;
import tech.inovasoft.inevolving.ms.objectives.repository.interfaces.ObjectiveJpaRepository;
import tech.inovasoft.inevolving.ms.objectives.repository.interfaces.ObjectiveRepository;

import java.util.List;
import java.util.UUID;

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

    @Override
    public Objective findByIdAndIdUser(UUID idObjective, UUID idUser) {
        //TODO: Desenvolver Método para o teste passar.
        //TODO: Refatorar Código.
        return null;
    }

    @Override
    public List<Objective> findAllByIdUser(UUID idUser) {
        //TODO: Desenvolver Teste Que Falhe.
        //TODO: Desenvolver Método para o teste passar.
        //TODO: Refatorar Código.
        return List.of();
    }

    @Override
    public List<Objective> findAllByIdUserAndStatus(UUID idUser, String todo) {
        //TODO: Desenvolver Teste Que Falhe.
        //TODO: Desenvolver Método para o teste passar.
        //TODO: Refatorar Código.
        return List.of();
    }
}
