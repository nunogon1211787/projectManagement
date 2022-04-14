package switch2021.project.dto;

import lombok.Getter;
import switch2021.project.model.UserStory.UsHour;
import switch2021.project.model.UserStory.UsPriority;
import switch2021.project.model.UserStory.UsTitle;
import switch2021.project.model.UserStory.UserStoryId;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.UserStoryStatus;

@Getter
public class UserStoryDto {

    /**
     * Attributes
     **/

    private UsTitle title;
    private UsPriority priority;
    private Description description;
    private UsHour timeEstimate;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public UserStoryDto(String title, int priority, String description, int timeEstimate) {
        this.title = new UsTitle(title);
        this.priority = new UsPriority(priority);
        this.description = new Description(description);
        this.timeEstimate = new UsHour(timeEstimate);
    }

}
