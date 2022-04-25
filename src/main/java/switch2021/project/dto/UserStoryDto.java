package switch2021.project.dto;

import lombok.Getter;
import switch2021.project.model.UserStory.UsHour;
import switch2021.project.model.UserStory.UsPriority;
import switch2021.project.model.UserStory.UsTitle;
import switch2021.project.model.UserStory.UserStoryID;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ProjectID;

@Getter
public class UserStoryDto {

    /**
     * Attributes
     **/

    private final ProjectID projectId;
    private final UserStoryID userStoryId;
    private final UsTitle title;
    private final UsPriority priority;
    private final Description description;
    private final UsHour timeEstimate;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public UserStoryDto(String projectId, String userStoryId, String title, int priority, String description, int timeEstimate) {
        this.projectId = new ProjectID(projectId);
        this.userStoryId = new UserStoryID(userStoryId);
        this.title = new UsTitle(title);
        this.priority = new UsPriority(priority);
        this.description = new Description(description);
        this.timeEstimate = new UsHour(timeEstimate);
    }

}
