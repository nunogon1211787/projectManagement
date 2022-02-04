package switch2021.project.stores;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.model.*;
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

    private final List<Sprint> sprintList;


    /**
     * Constructors with data
     **/

    public SprintList() {
        this.sprintList = new ArrayList<>();
    }


    /**
     * Sprint creator
     **/

    public Sprint createSprint(String name, LocalDate startDate, int sprintDuration) {

        Sprint sprint = null;

        if(validateStartDate(startDate)) {

            sprint = new Sprint(name, startDate);
            sprint.changeEndDate(sprintDuration);

        }

        return sprint;
    }

    /**
     * ID_Sprint Generator
     */

    private int idSprintGenerator() {
        int id = 1;
        if (this.sprintList.size() > 0) {
            id = (this.sprintList.get(sprintList.size() - 1).getIdSprint() + 1);
        }
        return id;
    }

    /**
     * Sprint Methods
     */

    public Sprint getSprint(int id) {
        Sprint sprint = null;
        for (Sprint sprt : sprintList) {
            if (sprt.hasSprintID(id)) {
                sprint = sprt;
                break;
            }
        }
        return sprint;
    }

    /**
     * Add and Remove Sprint Methods. Adds or remove a Sprint object to the Sprint List
     **/

    private boolean addSprint(Sprint sprint) {
        if (!validateIdSprint(sprint)) {
            sprint.setIdSprint(idSprintGenerator());
        }
        this.sprintList.add(sprint);
        return true;
    }


    /**
     * Get Method
     **/

    public List<Sprint> getSprintList() {

        return new ArrayList<>(this.sprintList);
    }


    /**
     * Method to Validate a Sprint
     **/

    public boolean validateIfSprintAlreadyExists(Sprint sprint) {
        return this.sprintList.contains(sprint);
    }

    private boolean validateIdSprint(Sprint sprint) {
        boolean msg = true;

        for (Sprint i : this.sprintList) {
            if (i.getIdSprint() == sprint.getIdSprint()) {
                msg = false;
                break;
            }
        }
        return msg;
    }

    /**
     * Method to Validate if StartDate is later than the EndDate of the last Sprint
     */

    private boolean validateStartDate(LocalDate startDate) {

        boolean msg = true;

        for (int i = 0; i < sprintList.size() - 1; i++) {
            if (!sprintList.get(i).getEndDate().isBefore(startDate) || sprintList.get(i).getEndDate().isEqual(startDate)) {
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
            addSprint(sprint);
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
        for(Sprint i : this.sprintList) {
            if(i.isCurrentSprint()) {
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

        for (Sprint i: sprintList){
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
        return sprintList.equals(that.sprintList);
    }

    /**
     * Hash
     */

    @Override
    public int hashCode() {
        return Objects.hash(sprintList);
    }
}