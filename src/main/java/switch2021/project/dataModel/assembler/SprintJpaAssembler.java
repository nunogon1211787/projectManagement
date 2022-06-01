package switch2021.project.dataModel.assembler;

import org.springframework.stereotype.Component;
import switch2021.project.dataModel.jpa.SprintJpa;
import switch2021.project.dataModel.jpa.UserStoryOfSprintJpa;
import switch2021.project.entities.aggregates.Sprint.Sprint;
import switch2021.project.entities.valueObjects.vos.UserStoryOfSprint;
import switch2021.project.entities.valueObjects.vos.enums.UserStoryOfSprintStatus;

import java.time.LocalDate;
import java.util.List;

@Component
public class SprintJpaAssembler {

    public SprintJpa toData(Sprint sprint) {
        String startDate = null;
        String endDate = null;
        if (!(sprint.getStartDate() == null)) {
            startDate = sprint.getStartDate().toString();
        }
        if (!(sprint.getEndDate() == null)) {
            endDate = sprint.getEndDate().toString();
        }
        SprintJpa sprintJpa = new SprintJpa(sprint.getSprintID(), startDate, endDate);

        List<UserStoryOfSprint> uSOfSprintList = sprint.getScrumBoardUserStoriesOfSprint();
        for (UserStoryOfSprint uSOfSprint : uSOfSprintList) {
            String status = null;
            if (!(uSOfSprint.getUserStoryOfSprintStatus() == null)) {
                status = uSOfSprint.getUserStoryOfSprintStatus().toString();
            }
            UserStoryOfSprintJpa userStoryOfSprintJpa = new UserStoryOfSprintJpa(uSOfSprint.getUserStoryId(), status,
                    sprintJpa);
            sprintJpa.getUSOfSprintJpaList().add(userStoryOfSprintJpa);
        }
        return sprintJpa;
    }

    public Sprint toDomain(SprintJpa sprintJpaSaved) {
        LocalDate startDate = null;
        LocalDate endDate = null;
        if (!(sprintJpaSaved.getStartDate() == null)) {
            startDate = LocalDate.parse(sprintJpaSaved.getStartDate());
        }
        if (!(sprintJpaSaved.getEndDate() == null)) {
            endDate = LocalDate.parse(sprintJpaSaved.getEndDate());
        }
        Sprint sprint = new Sprint(sprintJpaSaved.getId(), startDate, endDate);

        List<UserStoryOfSprintJpa> uSOfSprintJpaList = sprintJpaSaved.getUSOfSprintJpaList();
        for (UserStoryOfSprintJpa uSOfSprintJpa : uSOfSprintJpaList) {
            UserStoryOfSprintStatus status = null;
            if (!(uSOfSprintJpa.getStatus() == null)) {
                status = UserStoryOfSprintStatus.valueOf(uSOfSprintJpa.getStatus());
            }
            UserStoryOfSprint userStoryOfSprint = new UserStoryOfSprint(uSOfSprintJpa.getUserStoryId(), status);
            sprint.getScrumBoardUserStoriesOfSprint().add(userStoryOfSprint);
        }
        return sprint;
    }
}
