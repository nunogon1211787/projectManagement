package switch2021.project.model.Project;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
public enum ProjectStatusEnum {

    PLANNED,
    INCEPTION,
    ELABORATION,
    CONSTRUCTION,
    TRANSITION,
    WARRANTY,
    CLOSED

}
