package switch2021.project.stores;

import switch2021.project.model.ProjectRole;
import switch2021.project.model.Sprint;
import switch2021.project.model.SystemUser;
import switch2021.project.model.Typology;

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
     * Sprint Methods
     */


    public Sprint getSprint(long id) {
        Sprint sprint = null;
        for (Sprint sprt : sprintList) {
            if (sprt.getNumber() == id) {
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
     * ID Generator to the Sprint ID
     */

    public int idGeneratorSprint() {
        int id = getSprintList().size();
        return id;
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