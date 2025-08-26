package tech.inovasoft.inevolving.ms.objectives.service.client;

import java.sql.Date;
import java.util.UUID;

public record Task(
        UUID id,
        String nameTask,
        String descriptionTask,
        String status,
        Date dateTask,
        UUID idObjective,
        UUID idUser,
        UUID idParentTask,
        UUID idOriginalTask,
        Boolean hasSubtasks,
        Boolean blockedByObjective,
        Boolean isCopy,
        String cancellationReason
) {
}
