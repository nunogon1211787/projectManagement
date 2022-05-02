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
import switch2021.project.utils.Entity;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Sprint implements Entity<Sprint> {

    /**
     * Attributes of Sprint
     **/
    private SprintID sprintID;
    private ScrumBoard scrumBoard;
    private LocalDate startDate;
    private LocalDate endDate;


    /**
     * Constructor of Sprint
     **/
    public Sprint(SprintID sprintID) {
        this.sprintID = sprintID;
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
        return Objects.equals(projectID, projectID);
    }


    /**
     * Check if this Sprint is the current Sprint
     */
    public boolean isCurrentSprint() {
        if(this.endDate == null) {
            throw new NullPointerException();
        }
        return ((this.startDate.isBefore(endDate) || this.startDate.equals(endDate))
                && (this.endDate.isAfter(startDate) || this.endDate.equals(startDate)));
    }

//    /**
//     * Method to get list of tasks within a sprint - Método David
//     */
//    //It hasn't tests
//    public List<Task> getListOfTasksOfASprint(){
//        List<Task> taskList2 = new ArrayList<>();
//
//        taskList2.addAll(this.taskStore.getTaskList());
//        return taskList2;
//    }

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

    @Override
    public boolean sameIdentityAs(Sprint other) {
        return other != null && sprintID.sameValueAs(other.sprintID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sprint)) return false;
        Sprint sprint = (Sprint) o;
        return sameIdentityAs(sprint);
    }

}








