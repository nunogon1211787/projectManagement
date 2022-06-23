package switch2021.project.dtoModel.mapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.dtoModel.dto.OutputSprintDTO;
import switch2021.project.dtoModel.dto.OutputUserStoryDTO;
import switch2021.project.entities.aggregates.Sprint.Sprint;
import switch2021.project.entities.aggregates.UserStory.UserStory;
import switch2021.project.interfaceAdapters.controller.SprintController;
import switch2021.project.interfaceAdapters.controller.UserStoryController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class SprintMapper {

    public OutputSprintDTO toDTO(Sprint newSprint) {
        String sprintID = newSprint.getSprintID().toString();
        String projectID = newSprint.getSprintID().getProjectID().getCode();
        String name = newSprint.getSprintID().getSprintName().getText();

        return new OutputSprintDTO(projectID, sprintID, name);
    }

    public CollectionModel<OutputSprintDTO> toCollectionDto(List<Sprint> sprints) {

        CollectionModel<OutputSprintDTO> result = CollectionModel.of(sprints.stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));

        //HATEOAS - Get All Sprints
        result.add(linkTo(methodOn(SprintController.class).showSprints()).withSelfRel());

        return result;
    }
}
