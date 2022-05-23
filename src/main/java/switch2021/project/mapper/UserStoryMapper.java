package switch2021.project.mapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.controller.UserStoryController;
import switch2021.project.dto.OutputUserStoryDTO;
import switch2021.project.model.UserStory.UserStory;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserStoryMapper {



    public OutputUserStoryDTO toDto(UserStory newUserStory) {

        int priority = newUserStory.getPriority().getPriorityUs();
        String description = newUserStory.getDescription().getText();
        double timeEstimate = newUserStory.getTimeEstimate().getUsHours();

        String userStoryID = newUserStory.getUserStoryID().getProjectID().getCode() + "&" + newUserStory.getUserStoryID().getUsTitle().getTitleUs();

        OutputUserStoryDTO result = new OutputUserStoryDTO(userStoryID, priority, description, timeEstimate);

        //Add HATEOAS to OUTPUT DTOs

        //Add self relation
        result.add(linkTo(methodOn(UserStoryController.class).showUserStoryRequested(result.id)).withSelfRel());

        //Add collection relation
        result.add(linkTo(methodOn(UserStoryController.class).showAllUserStories()).withRel("Collection"));

        //Add delete option
        result.add(linkTo(methodOn(UserStoryController.class).deleteAUserStory(result.id)).withRel("Delete"));

        return result;


    }

    public CollectionModel<OutputUserStoryDTO> toCollectionDto(List<UserStory> userStories) {

        CollectionModel<OutputUserStoryDTO> result = CollectionModel.of(userStories.stream()
                .map(us -> toDto(us))
                .collect(Collectors.toList()));

        //Add HATEOAS to OUTPUT DTOs

        //Add self relation
        result.add(linkTo(methodOn(UserStoryController.class).showAllUserStories()).withSelfRel());

        return result;


    }

}
