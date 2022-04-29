package switch2021.project.model.Sprint;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import switch2021.project.interfaces.SprintRepositoryInterface;
import switch2021.project.repositories.ProjectTeam;
import switch2021.project.model.Task.Task;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Repository
public class SprintStore implements SprintRepositoryInterface{

    /** Attributes **/
    private List<Sprint> sprints;

    /** Constructor **/
    public SprintStore() { this.sprints = new ArrayList<>(); }


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
    public List<Sprint> findSprints() {
        return new ArrayList<>(this.sprints);
    }

    /** Find Sprint By ID Method **/
    public Sprint findSprintById(String sprintID) {
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
            if (x.hasProjectID(projectID)) {
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
    public boolean saveSprint (Sprint sprint) {
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

    /**
     * Method to return all activities in a project
     */
    public List<Task> getListOfAllAActivitiesOfAProject() {
        List<Task> allActivitiesInAProject = new ArrayList<>();
        for (Sprint i : sprints) {
            allActivitiesInAProject.addAll(i.getListOfTasksOfASprint());
        }
        return allActivitiesInAProject;
    }

    /** Method to Validate a Sprint **/
    public boolean validateIfSprintAlreadyExists(Sprint sprint) {
        return this.sprints.contains(sprint);
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
            findSprintById(sprintID);
            //sprint.setStartDate(startDate);
        }
        return msg;
    }

    /** Override Methods **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SprintStore)) return false;
        SprintStore that = (SprintStore) o;
        return sprints.equals(that.sprints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprints);
    }
}