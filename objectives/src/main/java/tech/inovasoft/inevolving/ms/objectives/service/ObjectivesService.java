package tech.inovasoft.inevolving.ms.objectives.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.inovasoft.inevolving.ms.objectives.domain.dto.request.RequestCreateObjectiveDTO;
import tech.inovasoft.inevolving.ms.objectives.domain.dto.response.ResponseMessageDTO;
import tech.inovasoft.inevolving.ms.objectives.domain.exception.InternalErrorException;
import tech.inovasoft.inevolving.ms.objectives.domain.model.Objective;
import tech.inovasoft.inevolving.ms.objectives.domain.model.Status;
import tech.inovasoft.inevolving.ms.objectives.repository.interfaces.ObjectiveRepository;
import tech.inovasoft.inevolving.ms.objectives.service.client.TasksServiceClient;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ObjectivesService {

    @Autowired
    private ObjectiveRepository objectiveRepository;

    @Autowired
    private TasksServiceClient tasksService;

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

    /**
     * @description - Complete objective | Completa um objetivo
     * @param idObjective - Id of objective to be completed | Id do objetivo a ser concluído
     * @param conclusionDate - Date of objective completion | Data de conclusão do objetivo
     * @param idUser - Id of user who completed the objective | Id do usuário que concluiu o objetivo
     * @return - Returns a message indicating that the objective was successfully completed | Retorna uma mensagem indicando que o objetivo foi concluído com sucesso
     * @throws InternalErrorException - Error in lock tasks by objective | Erro ao bloquear tarefas por objetivo
     */
    public ResponseMessageDTO completeObjective(UUID idObjective, LocalDate conclusionDate, UUID idUser) throws InternalErrorException {
        Objective objective = objectiveRepository.findByIdAndIdUser(idObjective, idUser);

        var response = tasksService.lockTaskByObjective(Date.valueOf(conclusionDate), idUser,idObjective);

        if (response.getStatusCode().is2xxSuccessful()) {

            objective.setCompletionDate(Date.valueOf(conclusionDate));
            objective.setStatusObjective(Status.DONE);

            objectiveRepository.save(objective);

            return new ResponseMessageDTO("Objective successfully completed");

        } {
            //TODO: Criar Teste Da Falha
            throw new InternalErrorException("Error in lock tasks by objective!");
        }
    }

    public Objective getObjectiveById(UUID idObjective, UUID idUser) {
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
