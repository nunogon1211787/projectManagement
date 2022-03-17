package switch2021.project.dto;

import lombok.Getter;
import switch2021.project.immutable.Description;
import switch2021.project.model.UserStoryStatus;

@Getter
public class UserStoryDto {

    /**
     * Attributes
     **/

    private String name;
    private UserStoryStatus userStoryStatus;
    private int priority;
    private Description description;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public UserStoryDto(String name, UserStoryStatus userStoryStatus, int priority, String description) {
        this.name = name;
        this.userStoryStatus = userStoryStatus;
        this.priority = priority;
        this.description = new Description(description);
    }

}
