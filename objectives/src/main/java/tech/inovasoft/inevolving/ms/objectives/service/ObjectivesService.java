package tech.inovasoft.inevolving.ms.objectives.service;

import org.springframework.stereotype.Service;
import tech.inovasoft.inevolving.ms.objectives.domain.dto.request.RequestCreateObjectiveDTO;
import tech.inovasoft.inevolving.ms.objectives.domain.dto.response.ResponseMessageDTO;
import tech.inovasoft.inevolving.ms.objectives.domain.model.Objective;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ObjectivesService {

    public Objective addObjective(RequestCreateObjectiveDTO dto) {
        //TODO: Criar Teste Que Falhe
        //TODO: Desenvolver método para o teste passar.
        //TODO: Refatorar código e criar documentação.
        return null;
    }

    public Objective updateObjective(UUID idObjective, RequestCreateObjectiveDTO dto) {
        //TODO: Criar Teste Que Falhe
        //TODO: Desenvolver método para o teste passar.
        //TODO: Refatorar código e criar documentação.
        return null;
    }

    public ResponseMessageDTO completeObjective(UUID idObjective, LocalDate conclusionDate) {
        //TODO: Criar Teste Que Falhe
        //TODO: Desenvolver método para o teste passar.
        //TODO: Refatorar código e criar documentação.
        return null;
    }

    public Objective getObjectiveById(UUID idObjective) {
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
