package switch2021.project.dto;

import lombok.Getter;
import switch2021.project.model.UserStory.UsHour;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.UserStoryStatus;

@Getter
public class UserStoryDto {

    /**
     * Attributes
     **/

    private String title;
    private int priority;
    private Description description;
    private UsHour timeEstimate;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public UserStoryDto(String title, int priority, String description, int timeEstimate) {
        this.title = title;
        this.priority = priority;
        this.description = new Description(description);
        this.timeEstimate = new UsHour(timeEstimate);;
    }

}
