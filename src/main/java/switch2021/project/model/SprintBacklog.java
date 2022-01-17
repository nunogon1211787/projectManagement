package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;

public class SprintBacklog {

    /** Class Atribures **/

    private List<UserStory> sprintList;

    /** Class constructor **/
    public SprintBacklog() {
        this.sprintList = new ArrayList<>();
    }

    /** Getter **/
    public List<UserStory> getSprintList() {
        return sprintList;
    }

    /** Add spint to list **/
    public boolean addSprint(UserStory sprint) {
        this.sprintList.add(sprint);
        return true;
    }

}
