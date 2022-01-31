package switch2021.project.dto;

import java.util.List;

public class ScrumBoardDTO {

        private String userStoryName;
        private String userStoryStatus;
        private String userStoryDescription;
        private int userStoryTimeEstimate;
        private int userStoryPriority;

        public ScrumBoardDTO(String name, String status, String description, int timeEstimate, int priority) {
                this.userStoryName = name;
                this.userStoryStatus = status;
                this.userStoryDescription = description;
                this.userStoryTimeEstimate = timeEstimate;
                this.userStoryPriority = priority;
        }
}
