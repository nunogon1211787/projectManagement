package switch2021.project.dataModel.jpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switch2021.project.entities.valueObjects.vos.SprintID;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "SprintJpa")
@Table(name = "sprints")
@Getter
@Setter
@NoArgsConstructor
//@ToString
public class SprintJpa {
    @EmbeddedId
    private SprintID id;
    private String startDate;
    private String endDate;
    @OneToMany(mappedBy = "sprintJpa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserStoryOfSprintJpa> uSOfSprintJpaList;

    public SprintJpa(SprintID id, String startDate, String endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.uSOfSprintJpaList = new ArrayList<>();
    }
}
