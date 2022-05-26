package switch2021.project.mapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;
import switch2021.project.controller.UserProfileController;
import switch2021.project.controller.UserStoryController;
import switch2021.project.dto.UserProfileDTO;
import switch2021.project.model.UserProfile.UserProfile;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserProfileMapper {

    public UserProfileDTO toDto(UserProfile newUserProfile) {
        String userProfileName = newUserProfile.getUserProfileId().getUserProfileName().getText();

        UserProfileDTO result = new UserProfileDTO(userProfileName);

        //Add HATEOAS to OUTPUT DTOs

        //Add self relation
        result.add(linkTo(methodOn(UserProfileController.class).showUserProfileRequested(result.description)).withSelfRel());

        //Add collection relation
        result.add(linkTo(methodOn(UserProfileController.class).showAllProfiles()).withRel("Collection"));

        //Add edit option
        result.add(linkTo(methodOn(UserProfileController.class).editAUserProfile(result.description, result)).withRel("Edit"));

        //Add delete option
        result.add(linkTo(methodOn(UserProfileController.class).deleteAUserProfile(result.description)).withRel("Delete"));

        return result;

    }

    public CollectionModel<UserProfileDTO> toCollectionModel(List<UserProfile> profiles){

         CollectionModel<UserProfileDTO> result = CollectionModel.of(profiles.stream()
                .map(profile -> toDto(profile))
                .collect(Collectors.toList()));

        //Add HATEOAS to OUTPUT DTOs

        //Add self relation
        result.add(linkTo(methodOn(UserProfileController.class).showAllProfiles()).withSelfRel());

        return result;
    }

}
