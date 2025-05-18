package tech.inovasoft.inevolving.ms.objectives.repository.implementation;

import tech.inovasoft.inevolving.ms.objectives.domain.model.Objective;
import tech.inovasoft.inevolving.ms.objectives.repository.interfaces.ObjectiveRepository;

import java.util.List;
import java.util.UUID;

public class ObjectiveRepositoryImplementation implements ObjectiveRepository {

    @Override
    public Objective save(Objective newObjective) {
        //TODO: Desenvolver Teste Que Falhe.
        //TODO: Desenvolver Método para o teste passar.
        //TODO: Refatorar Código.
        return null;
    }

    @Override
    public Objective findByIdAndIdUser(UUID idObjective, UUID idUser) {
        //TODO: Desenvolver Teste Que Falhe.
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
