package tech.inovasoft.inevolving.ms.objectives.domain.dto.request;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

public record RequestCreateObjectiveDTO(
        String nameObjective,
        String descriptionObjective,
        UUID idUser
) {
}
