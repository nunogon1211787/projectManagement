package switch2021.project.stores;

import switch2021.project.model.Sprint;

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
     * add Srpint
     **/

    public boolean addSprint(Sprint sprint) {
        this.sprintList.add(sprint);
        return true;
    }
}