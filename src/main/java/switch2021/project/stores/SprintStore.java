package switch2021.project.stores;

import switch2021.project.model.*;
import switch2021.project.utils.App;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class SprintStore {

    /**
     * Atributos da Classe
     **/

    private List<Sprint> sprintList;

    /**
     * Constructors with data
     **/

    public SprintStore() {
        this.sprintList = new ArrayList<>();
    }

    /**
     * Sprint creator
     **/

    public Sprint createSprint(long id, String name, LocalDate startDate, Project sprintDuration) {

        Sprint sprint;

        sprint = new Sprint(id, name, startDate, sprintDuration);

        return sprint;
    }


    /**
     * Sprint Methods
     */


    public Sprint getSprint(long id) {
        Sprint sprint = null;
        for (Sprint sprt : sprintList) {
            if (sprt.getID() == id) {
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
        return this.sprintList;
    }

    /**
     * Method to Validate a Sprint
     **/

    public boolean validateSprint(Sprint sprint) {

        boolean msg = true;
        for (Sprint x : this.sprintList) {
            if (x.equals(sprint) || sprint == null) {
                msg = false;
                break;
            }
        }
        return msg;
    }

    /**
     * Method to Save a Sprint
     */

    public boolean saveSprint(Sprint sprint) {

        boolean result = true;

        if (!validateSprint(sprint)) {
            result = false;
        } else {
            this.sprintList.add(sprint);
        }
        return result;
    }
}