package switch2021.project.dtoModel.mapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import switch2021.project.entities.valueObjects.vos.UserProfileID;
import switch2021.project.interfaceAdapters.controller.UserController;
import switch2021.project.dtoModel.dto.OutputUserDTO;
import switch2021.project.dtoModel.dto.UpdateDataDTO;
import switch2021.project.entities.aggregates.User.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserMapper {

    public OutputUserDTO toDto(User user) {

        String username = user.getUserName().getText();
        String email = user.getUserId().getEmail().getEmailText();
        String function = user.getFunction().getText();
        String photo = user.getPhoto().getExtension();
        String active = user.isActive() ? "True" : "False";
        String[] assignedIdProfiles = profilesToDTO(user);

        OutputUserDTO outputUserDTO = new OutputUserDTO(username, email, function, photo, active, assignedIdProfiles);

        //Add HATEOAS to OutPut DTO
        outputUserDTO
                //Self Relation
                .add(linkTo(methodOn(UserController.class).getUser(outputUserDTO.email)).withSelfRel())
                //Collection Relation
                .add(linkTo(methodOn(UserController.class).showAllUsers()).withRel("Collection"))
                //Delete option
                .add(linkTo(methodOn(UserController.class).deleteUser(outputUserDTO.email)).withRel("Delete"))
                //Update
                .add(linkTo(methodOn(UserController.class).updatePersonalData(outputUserDTO.getEmail(),
                        new UpdateDataDTO())).withRel("Edit"));
        return outputUserDTO;
    }


    public CollectionModel<OutputUserDTO> toCollectionDTO(List<User> userList) {

        CollectionModel<OutputUserDTO> users = CollectionModel.of(userList.stream()
                .map(user -> toDto(user))
                .collect(Collectors.toList()));
        //HATEOAS
        // Add Self Relation
        users.add(linkTo(methodOn(UserController.class).showAllUsers()).withSelfRel());
        return users;
    }

    //Method to convert User Profile list in a String list of User Profiles
    private String[] profilesToDTO(User user) {
        ArrayList<UserProfile> profiles = new ArrayList<>();
        String[] profileToString = new String[user.getAssignedProfiles().size()];

        for (UserProfileID id : user.getAssignedProfiles()) {
            profiles.add(new UserProfile(id));
        }

        //TODO try to simplify this method
        for (int i = 0; i < profileToString.length; i++) {
            for (UserProfile profile : profiles) {
                profileToString[i] = profile.getUserProfileId().getUserProfileName().getText();
                i++;
            }
            break;
        }

        return profileToString;
    }
}
