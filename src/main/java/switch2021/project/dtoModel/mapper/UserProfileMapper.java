package switch2021.project.dtoModel.mapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import switch2021.project.dtoModel.dto.OutputUserProfileDTO;
import switch2021.project.interfaceAdapters.controller.UserProfileController;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserProfileMapper {

    public OutputUserProfileDTO toDTO(UserProfile newUserProfile) {
        String description = newUserProfile.getUserProfileId().getUserProfileName().getText();

        OutputUserProfileDTO outputUserProfileDTO = new OutputUserProfileDTO(description);

        return outputUserProfileDTO;
    }

    public CollectionModel<OutputUserProfileDTO> toCollectionDTO (List<UserProfile> userProfiles) {

        CollectionModel <OutputUserProfileDTO> result = CollectionModel.of(userProfiles.stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));

        //HATEOAS - Get all UserProfiles
        result.add(linkTo(methodOn(UserProfileController.class).showAllProfiles()).withSelfRel());

        return result;
    }
}
