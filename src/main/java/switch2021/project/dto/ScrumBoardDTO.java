package switch2021.project.dto;

import lombok.Getter;

import java.util.Objects;

@Getter
public class ScrumBoardDTO {

        private String userStoryName;
        private String userStoryStatus;
        private String userStoryDescription;
        private int userStoryTimeEstimate;
        private int userStoryPriority;
        private int userStoryId;

        public ScrumBoardDTO(String name, String status, String description, int timeEstimate, int priority, int userStoryId) {
                this.userStoryName = name;
                this.userStoryStatus = status;
                this.userStoryDescription = description;
                this.userStoryTimeEstimate = timeEstimate;
                this.userStoryPriority = priority;
                this.userStoryId = userStoryId;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                ScrumBoardDTO that = (ScrumBoardDTO) o;
                return userStoryTimeEstimate == that.userStoryTimeEstimate
                        && userStoryPriority == that.userStoryPriority
                        && userStoryId == that.userStoryId
                        && Objects.equals(userStoryName, that.userStoryName)
                        && Objects.equals(userStoryStatus, that.userStoryStatus)
                        && Objects.equals(userStoryDescription, that.userStoryDescription);
        }

        @Override
        public int hashCode() {
                return Objects.hash(userStoryName, userStoryStatus, userStoryDescription, userStoryTimeEstimate, userStoryPriority);
        }
}
