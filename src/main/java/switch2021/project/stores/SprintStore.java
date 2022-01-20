package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class SprintStore {

    /**
     * Atributos da Classe
     **/

    private final List<Sprint> sprintList;

    /**
     * Constructors with data
     **/

    public SprintStore() {
        this.sprintList = new ArrayList<>();
    }

    /**
     * Sprint creator
     **/

    public Sprint createSprint(String name, LocalDate startDate, int sprintDuration) {

        validateIfStartDate(startDate);

        Sprint sprint;

        long id = generateID();

        sprint = new Sprint(id, name, startDate);

        sprint.changeEndDate(sprintDuration);

        return sprint;
    }

    private long generateID() {
        long id = 1;
        if(this.sprintList.size() > 0) {
            id = this.sprintList.get(sprintList.size()-1).getId() + 1;
        }
        return id;
    }


    /**
     * Sprint Methods
     */


    public Sprint getSprint(long id) {
        Sprint sprint = null;
        for (Sprint sprt : sprintList) {
            if (sprt.getId() == id) {
                sprint = sprt;
                break;
            }
        }
        return sprint;
    }

    /**
     * Add Sprint
     **/

    public boolean addSprint(Sprint sprint) {
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

    /**
     * Method to Validate if StartDate is later than the EndDate of the last Sprint
     */

    private void validateIfStartDate (LocalDate startDate) {

        for (Sprint i : sprintList)
            if (i.getEndDate().isBefore(startDate) && i.getStartDate() != startDate)
                throw new IllegalArgumentException("Please type the correct Start Date.");
        }


    /**
     * Method to Save a Sprint
     */


    public boolean saveSprint(Sprint sprint) {

        boolean result = true;

        if (!validateIfSprintAlreadyExists(sprint)) {
            result = false;
        } else {
            this.sprintList.add(sprint);
        }
        return result;
    }


    /**
     * Get the start and end date of the current Sprint
     */
    public LocalDate getCurrentSprintEndDate() {
        LocalDate date = null;
        for(Sprint i : this.sprintList) {
            if(i.isCurrentSprint()) {
                date = i.getEndDate();
            }
        }
        return date;
    }
}