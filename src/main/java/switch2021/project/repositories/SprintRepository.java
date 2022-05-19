package switch2021.project.repositories;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import switch2021.project.interfaces.ISprintRepo;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.repositories.old.ProjectTeam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Repository
public class SprintRepository implements ISprintRepo {

    /** Attributes **/
    private final List<Sprint> sprints;

    /** Constructor **/
    public SprintRepository() { this.sprints = new ArrayList<>(); }


    /** Check If Sprint Already Exists */
    public boolean existsBySprintID (String sprintID) {
        boolean msg = false;
        for (Sprint sprint : sprints) {
            if(sprint.hasSprintID(sprintID)) {
                msg = true;
            }
        }
        return msg;
    }

    /** Find List of Sprints Method **/
    public List<Sprint> findAllSprints() {
        return new ArrayList<>(this.sprints);
    }

    /** Find Sprint By ID Method **/
    public Sprint findBySprintID(String sprintID) {
        Sprint sprint = null;
        for (Sprint sprt : sprints) {
            if (sprt.hasSprintID(sprintID)) {
                sprint = sprt;
                break;
            }
        }
        return sprint;
    }

    /** Find all sprints associated to a Project ID Method **/
    public List<Sprint> findAllSprintsByProjectID(String projectID) {
        List<Sprint> allSprintsInAProject = new ArrayList<>();
        for (Sprint x : sprints) {
            if (x.getSprintID().getProjectID().getCode().equalsIgnoreCase(projectID)) {
                allSprintsInAProject.add(x);
            }
        }
        return allSprintsInAProject;
    }

    /** Find Current Sprint Method **/
    public Sprint findCurrentSprint() {
        Sprint sprint = null;
        for (Sprint i : this.sprints) {
            if (i.isCurrentSprint()) {
                sprint = i;
            }
        }
        if (sprint == null) {
            throw new NullPointerException("Current sprint doesn't exist");
        }
        return sprint;
    }

    /** Save Sprint */
    public boolean save(Sprint sprint) {
        boolean msg = true;
        if (existsBySprintID(sprint.getSprintID().toString())){
            msg = false;
        } else {
            sprints.add(sprint);
        }
        return msg;
    }

    /** Delete Sprint Method **/
    public boolean deleteSprint (Sprint sprint){
        this.sprints.remove(sprint);
        return true;
    }

    /** Method to Validate if StartDate is later than the EndDate of the last Sprint **/
    private boolean validateStartDate(LocalDate startDate) {
        boolean msg = true;
        for (int i = 0; i < sprints.size() - 1; i++) {
            if (!sprints.get(i).getEndDate().isBefore(startDate) || sprints.get(i).getEndDate().isEqual(startDate)) {
                msg = false;
            }
        }
        return msg;
    }

    /** Method to Start the Sprint **/
    public boolean startASprint (String sprintID, LocalDate startDate, ProjectTeam projectTeam, int sprintDuration) {
        boolean msg = false;
        if (validateStartDate(startDate) && projectTeam.validateProjectTeam(startDate, sprintDuration)) {
            msg = true;
            findBySprintID(sprintID);
            //sprint.setStartDate(startDate);
        }
        return msg;
    }

    /** Override Methods **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SprintRepository)) return false;
        SprintRepository that = (SprintRepository) o;
        return sprints.equals(that.sprints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprints);
    }
}