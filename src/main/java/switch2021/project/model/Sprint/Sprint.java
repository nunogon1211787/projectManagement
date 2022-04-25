package switch2021.project.model.Sprint;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import switch2021.project.model.Task.Task;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.UserStory.UserStoryID;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.Task.TaskStore;
import switch2021.project.model.valueObject.ProjectID;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Sprint {

    /**
     * Attributes of Sprint
     **/
    private ProjectID projectID;
    private SprintID sprintID;
    private Description sprintName;
    private final TaskStore taskStore;
    private ScrumBoard scrumBoard;
    private LocalDate startDate;
    private LocalDate endDate;


    /**
     * Constructor of Sprint
     **/
    public Sprint(String projectID, String sprintID ,String name) {
        this.projectID = new ProjectID(projectID);
        this.sprintID = new SprintID(sprintID);
        this.sprintName = new Description(name);
        this.scrumBoard = new ScrumBoard();
        this.taskStore = new TaskStore();
    }

    /**
     * Method to change Sprint EndDate
     **/

    public void changeEndDate(int sprintDurationInDays) {
        this.endDate = LocalDate.now().plusDays(sprintDurationInDays -1L);
    }


    public boolean hasSprintID(String sprID) {
         return Objects.equals(this.sprintID.toString(), sprID);}

    public boolean hasProjectID(String projectID) {
        return Objects.equals(this.projectID.toString(), projectID);
    }


    /**
     * Check if this Sprint is the current Sprint
     */
    public boolean isCurrentSprint() {
        if(this.endDate == null) {
            throw new NullPointerException();
        }
        return ((this.startDate.isBefore(LocalDate.now()) || this.startDate.equals(LocalDate.now()))
                && (this.endDate.isAfter(LocalDate.now()) || this.endDate.equals(LocalDate.now())));
    }

    /**
     * Method to get list of tasks within a sprint
     */
    //It hasn't tests
    public List<Task> getListOfTasksOfASprint(){
        List<Task> taskList2 = new ArrayList<>();

        taskList2.addAll(this.taskStore.getTaskList());
        return taskList2;
    }

    /**
     * Methods to call methods from sprint backlog
     */

    public List<UserStory> getListOfUsFromScrumBoard(){
        return this.scrumBoard.getUserStoryList();
    }

    public UserStory getUsByIdFromScrumBoard(UserStoryID id){
        return this.scrumBoard.getUserStory(id);
    }

    public boolean saveUsInScrumBoard(UserStory userStory){
        scrumBoard.saveUserStoryToSprintBacklog(userStory);
        return true;
    }
    /**
     * Override Methods
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sprint)) return false;
        Sprint sprint = (Sprint) o;
        return sprintID == sprint.sprintID &&
                Objects.equals(projectID, sprint.projectID) &&
                Objects.equals(sprintName, sprint.sprintName) &&
                Objects.equals(taskStore, sprint.taskStore) &&
                Objects.equals(scrumBoard, sprint.scrumBoard) &&
                Objects.equals(startDate, sprint.startDate) &&
                Objects.equals(endDate, sprint.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, sprintID, sprintName, taskStore, scrumBoard, startDate, endDate);
    }
}








