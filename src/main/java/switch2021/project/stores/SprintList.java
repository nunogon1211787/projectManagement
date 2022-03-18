package switch2021.project.stores;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.factory.SprintFactory;
import switch2021.project.model.ProjectTeam;
import switch2021.project.model.Sprint;
import switch2021.project.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class SprintList {

    /**
     * Class Attributes
     **/
    //private SprintFactory sprintFactory;
    private final List<Sprint> sprints;

    /**
     * Constructors with data
     **/
    public SprintList() {
        this.sprints = new ArrayList<>();
    }

    /*public SprintList(SprintFactory sprintFact) {
        this.sprints = new ArrayList<>();
        this.sprintFactory = sprintFact;
    }*/

    /**
     * Sprint creator
     **/
    public Sprint createSprint(String name, LocalDate startDate, int sprintDuration) {
        Sprint sprint = null;

        if(validateStartDate(startDate)) {
            sprint = new Sprint(name, startDate);
            //sprint = this.sprintFactory.createSprint(name, startDate);
            sprint.changeEndDate(sprintDuration);
        }
        return sprint;
    }

    /**
     * ID_Sprint Generator
     */

    private int idSprintGenerator() {
        int id = 1;
        if (this.sprints.size() > 0) {
            id = (this.sprints.get(sprints.size() - 1).getIdSprint() + 1);
        }
        return id;
    }

    /**
     * Sprint Methods
     */

    public Sprint getSprint(int id) {
        Sprint sprint = null;
        for (Sprint sprt : sprints) {
            if (sprt.hasSprintID(id)) {
                sprint = sprt;
                break;
            }
        }
        return sprint;
    }

    /**
     * Get Method
     **/

    public List<Sprint> getSprints() {

        return new ArrayList<>(this.sprints);
    }


    /**
     * Method to Validate a Sprint
     **/

    public boolean validateIfSprintAlreadyExists(Sprint sprint) {
        return this.sprints.contains(sprint);
    }


    /**
     * Method to Validate if StartDate is later than the EndDate of the last Sprint
     */

    private boolean validateStartDate(LocalDate startDate) {

        boolean msg = true;

        for (int i = 0; i < sprints.size() - 1; i++) {
            if (!sprints.get(i).getEndDate().isBefore(startDate) || sprints.get(i).getEndDate().isEqual(startDate)) {
                msg = false;
            }
        }
        return msg;
    }

    /**
     * Method to Start the Sprint
     */

    public boolean startASprint (int sprintID, LocalDate startDate, ProjectTeam projectTeam, int sprintDuration) {

        boolean msg = false;

        if (validateStartDate(startDate) && projectTeam.validateProjectTeam(startDate, sprintDuration)) {
            msg = true;
            Sprint sprint = getSprint(sprintID);
            sprint.setStartDate(startDate);
        }
        return msg;
    }

    /**
     * Method to Save a Sprint
     */

    public boolean saveSprint(Sprint sprint) {

        boolean result = true;

        if (validateIfSprintAlreadyExists(sprint)) {
            result = false;
        } else {
            sprint.setIdSprint(idSprintGenerator());
            this.sprints.add(sprint);
        }
        return result;
    }


//    /**
//     * Get the start and end date of the current Sprint
//     */
//    public Sprint getNextSprint() {
//        Sprint nextSprint = null;
//        for(Sprint i : this.sprintList) {
//            if(i.getStartDate().isAfter(getCurrentSprint().getEndDate())) {
//                nextSprint = i;
//                break;
//            }
//        }
//        if(nextSprint == null) {
//            throw new NullPointerException("Do not exist the next sprint.");
//        }
//        return nextSprint;
//    }

    public Sprint getCurrentSprint() {
        Sprint sprint = null;
        for (Sprint i : this.sprints) {
            if (i.isCurrentSprint()) {
                sprint = i;
            }
        }
        if(sprint == null) {
            throw new NullPointerException("Do not exist a current sprint.");
        }
        return sprint;
    }

    /**
     * Method to return all activities in a project
     */

    public List<Task> getListOfAllAActivitiesOfAProject(){
        List<Task> allActivitiesInAProject = new ArrayList<>();

        for (Sprint i : sprints) {
            allActivitiesInAProject.addAll(i.getListOfTasksOfASprint());
        }
        return allActivitiesInAProject;
    }

    /**
     * Override
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SprintList)) return false;
        SprintList that = (SprintList) o;
        return sprints.equals(that.sprints);
    }

    /**
     * Hash
     */

    @Override
    public int hashCode() {
        return Objects.hash(sprints);
    }
}