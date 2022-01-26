package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class SprintList {

    /**
     * Atributos da Classe
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
        validateStartDate(startDate);

        Sprint sprint;

        sprint = new Sprint(name, startDate);
        sprint.changeEndDate(sprintDuration);

        return sprint;
    }


    /**
     * ID_Sprint Generator
     */
    private int id_SprintGenerator() {
        int id = 1;
        if(this.sprintList.size() > 0) {
            id = (this.sprintList.get(sprintList.size()-1).getId_Sprint() + 1);
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
        if (!validateId_Sprint(sprint)) {
            sprint.setId_Sprint(id_SprintGenerator());
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

    private boolean validateId_Sprint(Sprint sprint) {
        boolean msg = true;

        for (Sprint i : this.sprintList) {
            if (i.getId_Sprint() == sprint.getId_Sprint()) {
                msg = false;
                break;
            }
        }
        return msg;
    }



    /**
     * Method to Validate if StartDate is later than the EndDate of the last Sprint
     */

    private void validateStartDate(LocalDate startDate) {

        for (Sprint i : sprintList)
            if (!i.getEndDate().isBefore(startDate) || i.getEndDate().isEqual(startDate))
                throw new IllegalArgumentException("Please type the correct Start Date.");
        }


    /**
     * Method to Save a Sprint
     */
    public boolean saveSprint(Sprint sprint) {

        boolean result = true;

        if (validateIfSprintAlreadyExists(sprint)) {
            result = false;
        } else {
            sprint.setId_Sprint(id_SprintGenerator());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SprintList)) return false;
        SprintList that = (SprintList) o;
        return sprintList.equals(that.sprintList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintList);
    }
}