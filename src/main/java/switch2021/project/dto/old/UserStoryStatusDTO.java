package switch2021.project.dto.old;

import lombok.Getter;
import switch2021.project.model.valueObject.UsHour;
import switch2021.project.model.valueObject.UsPriority;
import switch2021.project.model.valueObject.UsTitle;
import switch2021.project.model.valueObject.UserStoryID;
import switch2021.project.model.valueObject.Description;

@Getter
public class UserStoryStatusDTO {

        /**
         * Attributes
         **/

        private final UserStoryID userStoryId;
        private final UsTitle title;
        private final Description description;
        private final UsHour timeEstimate;
        private final UsPriority priority;

        /**
         * Constructor to test (without SINGLETON)
         **/

        public UserStoryStatusDTO(String userStoryId,String title, String description, double timeEstimate, int priority) {
                this.title = new UsTitle(title);
                this.description = new Description(description);
                this.timeEstimate = new UsHour(timeEstimate);
                this.priority = new UsPriority(priority);
                this.userStoryId = new UserStoryID(userStoryId);
        }

}
