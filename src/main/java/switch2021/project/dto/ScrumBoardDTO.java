package switch2021.project.dto;

import lombok.Getter;

@Getter
public class ScrumBoardDTO {

        /**
         * Attributes
         **/

        private final String userStoryName;
        private final String userStoryStatus;
        private final String userStoryDescription;
        private final int userStoryTimeEstimate;
        private final int userStoryPriority;
        private final int userStoryId;

        /**
         * Constructor to test (without SINGLETON)
         **/

        public ScrumBoardDTO(String name, String status, String description, int timeEstimate, int priority, int userStoryId) {
                this.userStoryName = name;
                this.userStoryStatus = status;
                this.userStoryDescription = description;
                this.userStoryTimeEstimate = timeEstimate;
                this.userStoryPriority = priority;
                this.userStoryId = userStoryId;
        }

}
