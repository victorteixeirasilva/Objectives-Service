package tech.inovasoft.inevolving.ms.objectives.service;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.inovasoft.inevolving.ms.objectives.domain.dto.request.RequestCreateObjectiveDTO;
import tech.inovasoft.inevolving.ms.objectives.domain.dto.response.ResponseMessageDTO;
import tech.inovasoft.inevolving.ms.objectives.domain.exception.DataBaseException;
import tech.inovasoft.inevolving.ms.objectives.domain.exception.InternalErrorException;
import tech.inovasoft.inevolving.ms.objectives.domain.exception.NotFoundObjectivesByUser;
import tech.inovasoft.inevolving.ms.objectives.domain.exception.NotFoundObjectivesByUserAndStatus;
import tech.inovasoft.inevolving.ms.objectives.domain.model.Objective;
import tech.inovasoft.inevolving.ms.objectives.domain.model.Status;
import tech.inovasoft.inevolving.ms.objectives.repository.interfaces.ObjectiveJpaRepository;
import tech.inovasoft.inevolving.ms.objectives.repository.interfaces.ObjectiveRepository;
import tech.inovasoft.inevolving.ms.objectives.service.client.Auth_For_MService.MicroServices;
import tech.inovasoft.inevolving.ms.objectives.service.client.Auth_For_MService.TokenCache;
import tech.inovasoft.inevolving.ms.objectives.service.client.Task;
import tech.inovasoft.inevolving.ms.objectives.service.client.TasksServiceClient;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static tech.inovasoft.inevolving.ms.objectives.service.client.Auth_For_MService.MicroServices.TASKS_SERVICE;

@Service
public class ObjectivesService {

    @Autowired
    private ObjectiveRepository objectiveRepository;

    @Autowired
    private TasksServiceClient tasksService;

    @Autowired
    private ObjectiveJpaRepository objectiveJpaRepository;

    @Autowired
    private TokenCache tokenCache;

    private String cachedToken;

    private String getValidTokenGateway() {
        if (cachedToken == null) {
            cachedToken = tokenCache.getToken(TASKS_SERVICE);
        }
        return cachedToken;
    }

    /**
     * @description - Add a new objective | Adiciona um novo objetivo
     * @param dto - RequestCreateObjectiveDTO (Data Transfer Object) - Objeto de transferência de dados
     * @return  - Returns the new registered objective. | Retorna o novo objetivo cadastrado.
     */
    public Objective addObjective(RequestCreateObjectiveDTO dto) throws DataBaseException {
        var newObjective = new Objective(dto);
        return objectiveRepository.save(newObjective);
    }

    /**
     * @description - Update objective | Atualiza um objetivo
     * @param idObjective - Id of objective to be updated | Id do objetivo a ser atualizado
     * @param dto - RequestCreateObjectiveDTO (Data Transfer Object) - Objeto de transferência de dados
     * @return - Returns the updated objective | Retorna o objetivo atualizado
     */
    public Objective updateObjective(UUID idObjective, RequestCreateObjectiveDTO dto, UUID idUser) throws DataBaseException, NotFoundObjectivesByUser {
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
    public ResponseMessageDTO completeObjective(UUID idObjective, LocalDate conclusionDate, UUID idUser) throws InternalErrorException, DataBaseException, NotFoundObjectivesByUser {
        Objective objective = objectiveRepository.findByIdAndIdUser(idObjective, idUser);

        ResponseEntity<ResponseMessageDTO> response;

        try {
            response = tasksService.lockTaskByObjective(Date.valueOf(conclusionDate), idUser,idObjective, getValidTokenGateway());
        } catch (FeignException.Unauthorized unauthorized) {
            cachedToken = null;
            return completeObjective(idObjective, conclusionDate, idUser);
        } catch (Exception e) {
            response = null;
        }

        if (response != null && response.getStatusCode().is2xxSuccessful()) {

            objective.setCompletionDate(Date.valueOf(conclusionDate));
            objective.setStatusObjective(Status.DONE);

            objectiveRepository.save(objective);

            return new ResponseMessageDTO("Objective successfully completed");

        } {
            throw new InternalErrorException("Error in lock tasks by objective!");
        }
    }

    /**
     * @description - Get objective by id | Busca um objetivo pelo id
     * @param idObjective - Id of objective to be searched | Id do objetivo a ser buscado
     * @param idUser - Id of user who searched the objective | Id do usuário que buscou o objetivo
     * @return - Returns the objective found | Retorna o objetivo encontrado
     */
    public Objective getObjectiveById(UUID idObjective, UUID idUser) throws DataBaseException, NotFoundObjectivesByUser {
        return objectiveRepository.findByIdAndIdUser(idObjective, idUser);
    }

    /**
     * @description - Get objectives by id user | Busca os objetivos pelo id do usuário
     * @param idUser - Id of user who searched the objectives | Id do usuário que buscou os objetivos
     * @return - Returns the objectives found | Retorna os objetivos encontrados
     * @throws NotFoundObjectivesByUser - Error in get objectives by id user | Erro ao buscar objetivos pelo id do usuário
     */
    public List<Objective> getObjectivesByIdUser(UUID idUser) throws NotFoundObjectivesByUser, DataBaseException {
        List<Objective> objectives = objectiveRepository.findAllByIdUser(idUser);

        if (objectives.isEmpty()) {
            throw new NotFoundObjectivesByUser();
        }

        return objectives;
    }

    /**
     * @description - Get objectives by id user and status | Busca os objetivos pelo id do usuário e status
     * @param idUser - Id of user who searched the objectives | Id do usuário que buscou os objetivos
     * @param status - Status of objective to be searched | Status do objetivo a ser buscado
     * @return - Returns the objectives found | Retorna os objetivos encontrados
     * @throws NotFoundObjectivesByUserAndStatus - Error in get objectives by id user and status | Erro ao buscar objetivos pelo id do usuário e status
     */
    public List<Objective> getObjectivesByIdUserStatus(UUID idUser, String status) throws NotFoundObjectivesByUserAndStatus, DataBaseException {
        List<Objective> objectives = objectiveRepository.findAllByIdUserAndStatus(idUser, status);

        if (objectives.isEmpty()) {
            throw new NotFoundObjectivesByUserAndStatus();
        }

        return objectives;
    }

    public ResponseMessageDTO removeObjectiveById(UUID idObjective, UUID idUser) throws DataBaseException, NotFoundObjectivesByUser {
        Objective objective = getObjectiveById(idObjective, idUser);

        List<Task> taskList;
        try {
            ResponseEntity<List<Task>> ResponseTaskList = tasksService.getTasksByObjectiveId(idUser, idObjective, getValidTokenGateway());
            taskList = ResponseTaskList.getBody();
        } catch (FeignException.Unauthorized unauthorized) {
            cachedToken = null;
            return removeObjectiveById(idObjective, idUser);
        } catch (FeignException.NotFound e) {
            taskList = new ArrayList<>();
        }

        if (!taskList.isEmpty()){
            for (Task task : taskList) {
                try {
                    tasksService.deleteTask(idUser, task.id(), getValidTokenGateway());
                } catch (FeignException.Unauthorized unauthorized) {
                    cachedToken = null;
                    tasksService.deleteTask(idUser, task.id(), getValidTokenGateway());
                }
            }
        }

        objectiveJpaRepository.deleteById(objective.getId());

        return new ResponseMessageDTO("Objective successfully removed");
    }
}
