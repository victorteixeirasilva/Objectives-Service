package tech.inovasoft.inevolving.ms.objectives.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.inovasoft.inevolving.ms.objectives.domain.dto.request.RequestCreateObjectiveDTO;
import tech.inovasoft.inevolving.ms.objectives.domain.dto.response.ResponseMessageDTO;
import tech.inovasoft.inevolving.ms.objectives.domain.model.Objective;
import tech.inovasoft.inevolving.ms.objectives.repository.interfaces.ObjectiveRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ObjectivesService {

    @Autowired
    private ObjectiveRepository objectiveRepository;

    /**
     * @description - Add a new objective | Adiciona um novo objetivo
     * @param dto - RequestCreateObjectiveDTO (Data Transfer Object) - Objeto de transferência de dados
     * @return  - Returns the new registered objective. | Retorna o novo objetivo cadastrado.
     */
    public Objective addObjective(RequestCreateObjectiveDTO dto) {
        var newObjective = new Objective(dto);
        return objectiveRepository.save(newObjective);
    }

    /**
     * @description - Update objective | Atualiza um objetivo
     * @param idObjective - Id of objective to be updated | Id do objetivo a ser atualizado
     * @param dto - RequestCreateObjectiveDTO (Data Transfer Object) - Objeto de transferência de dados
     * @return - Returns the updated objective | Retorna o objetivo atualizado
     */
    public Objective updateObjective(UUID idObjective, RequestCreateObjectiveDTO dto, UUID idUser) {
        var oldObjective = objectiveRepository.findByIdAndIdUser(idObjective, idUser);

        oldObjective.setNameObjective(dto.nameObjective());
        oldObjective.setDescriptionObjective(dto.descriptionObjective());

        return objectiveRepository.save(oldObjective);
    }

    public ResponseMessageDTO completeObjective(UUID idObjective, LocalDate conclusionDate, UUID idUser) {
        //TODO: Desenvolver método para o teste passar.
        //TODO: Refatorar código e criar documentação.
        return null;
    }

    public Objective getObjectiveById(UUID idObjective, UUID idUser) {
        //TODO: Criar Teste Que Falhe
        //TODO: Desenvolver método para o teste passar.
        //TODO: Refatorar código e criar documentação.
        return null;
    }

    public List<Objective> getObjectivesByIdUser(UUID idUser) {
        //TODO: Criar Teste Que Falhe
        //TODO: Desenvolver método para o teste passar.
        //TODO: Refatorar código e criar documentação.
        return null;
    }

    public List<Objective> getObjectivesByIdUserStatus(UUID idUser, String status) {
        //TODO: Criar Teste Que Falhe
        //TODO: Desenvolver método para o teste passar.
        //TODO: Refatorar código e criar documentação.
        return null;
    }
}
