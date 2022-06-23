package switch2021.project.dtoModel.mapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.dtoModel.dto.UserStoryOfSprintDTO;
import switch2021.project.entities.valueObjects.vos.UserStoryOfSprint;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserStoryOfSprintMapper {

    public UserStoryOfSprintDTO toDTO(UserStoryOfSprint userStoryOfSprint) {
        UserStoryOfSprintDTO userStoryOfSprintDTO = new UserStoryOfSprintDTO();

        userStoryOfSprintDTO.projectId = userStoryOfSprint.getUserStoryId().getProjectID().getCode();
        userStoryOfSprintDTO.usTitle = userStoryOfSprint.getUserStoryId().getUsTitle().getTitleUs();
        userStoryOfSprintDTO.sprintName =
                userStoryOfSprintDTO.status = userStoryOfSprint.getUserStoryOfSprintStatus().toString();

        return userStoryOfSprintDTO;
    }


    public CollectionModel<UserStoryOfSprintDTO> toCollectionDTO(List<UserStoryOfSprint> userStoryOfSprintList) {

        return CollectionModel.of(userStoryOfSprintList.stream().map(this::toDTO).collect(Collectors.toList()));
    }
}
