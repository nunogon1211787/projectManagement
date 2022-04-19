package switch2021.project.factory;

import switch2021.project.dto.UserStoryDto;
import switch2021.project.factoryInterface.UserStoryFactoryInterface;
import switch2021.project.model.UserStory.UserStory;

public class UserStoryFactory implements UserStoryFactoryInterface {


    @Override
    public UserStory createUserStory(String userStoryId, String title, int priority, String description, int estimateEffort) {
        return new UserStory(userStoryId,  title, priority, description, estimateEffort);
    }

    public UserStory createUserStoryWithDto(UserStoryDto userStoryDto) {
        return new UserStory(userStoryDto.getUserStoryId().toString(), userStoryDto.getTitle().getTitleUs(), userStoryDto.getPriority().getPriorityUs(), userStoryDto.getDescription().getText(),userStoryDto.getTimeEstimate().getUsHours());
    }
}