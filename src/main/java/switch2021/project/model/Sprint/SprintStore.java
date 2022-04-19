package switch2021.project.model.Sprint;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.factoryInterface.SprintFactoryInterface;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.repositories.ProjectTeam;
import switch2021.project.model.Task.Task;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class SprintStore {

    /** Class Attributes **/
    private final List<Sprint> sprints;
    private SprintFactoryInterface sprintFactory;


    /** Constructors with data **/
    public SprintStore(SprintFactoryInterface sprintFactoryInterface) {
        this.sprints = new ArrayList<>();
        this.sprintFactory = sprintFactoryInterface;
    }

    /** Sprint creator **/
    public Sprint createAndSaveSprint(int projectID, String sprintID, String name, int sprintDuration) {
        Sprint sprint = this.sprintFactory.createSprint(projectID, sprintID, name);
        validateIfSprintAlreadyExists(sprint);
        sprint.changeEndDate(sprintDuration);
        this.sprints.add(sprint);
        return sprint;
    }

//    /** Method to Save a Sprint **/
//    public boolean saveSprint(Sprint sprint) {
//        boolean result = true;
//        if (validateIfSprintAlreadyExists(sprint)) {
//            result = false;
//        } else {
//            sprint.setSprintID(idSprintGenerator());
//            this.sprints.add(sprint);
//        }
//        return result;
//    }

//    /**
//     * ID_Sprint Generator
//     */
//    private int idSprintGenerator() {
//        int id = 1;
//        if (this.sprints.size() > 0) {
//            id = (this.sprints.get(sprints.size() - 1).getSprintID() + 1);
//        }
//        return id;
//    }


    /** Find List of Sprints Method **/
    public List<Sprint> findSprints() {
        return new ArrayList<>(this.sprints);
    }

    /** Find Sprint By ID Method **/
    public Sprint findSprintById(SprintID sprintID) {
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
    public List<Sprint> findAllSprintsByProjectID(ProjectID projectID) {
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
    public boolean startASprint (SprintID sprintID, LocalDate startDate, ProjectTeam projectTeam, int sprintDuration) {
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