package tech.inovasoft.inevolving.ms.objectives.domain.model;

import jakarta.persistence.*;
import lombok.*;
import tech.inovasoft.inevolving.ms.objectives.domain.dto.request.RequestCreateObjectiveDTO;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "objectives")
public class Objective {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nameObjective;
    private String descriptionObjective;
    private String statusObjective;
    private Date completionDate;
    private UUID idUser;

    public Objective(RequestCreateObjectiveDTO requestCreateObjectiveDTO) {
        this.nameObjective = requestCreateObjectiveDTO.nameObjective();
        this.descriptionObjective = requestCreateObjectiveDTO.descriptionObjective();
        this.statusObjective = requestCreateObjectiveDTO.statusObjective();
        this.completionDate = Date.valueOf(requestCreateObjectiveDTO.completionDate());
        this.idUser = requestCreateObjectiveDTO.idUser();
    }
}
