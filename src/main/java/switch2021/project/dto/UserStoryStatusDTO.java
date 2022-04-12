package switch2021.project.dto;

import lombok.Getter;

@Getter
public class UserStoryStatusDTO {

        /**
         * Attributes
         **/

        private final String userStoryTitle;
        private final String userStoryDescription;
        private final int userStoryTimeEstimate;
        private final int userStoryPriority;
        private final int userStoryId;

        /**
         * Constructor to test (without SINGLETON)
         **/

        public UserStoryStatusDTO(String title, String description, int timeEstimate, int priority, int userStoryId) {
                this.userStoryTitle = title;
                this.userStoryDescription = description;
                this.userStoryTimeEstimate = timeEstimate;
                this.userStoryPriority = priority;
                this.userStoryId = userStoryId;
        }

}
