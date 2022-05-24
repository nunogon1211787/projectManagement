package switch2021.project.mapper;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;
import switch2021.project.controller.UserController;
import switch2021.project.dto.OutputUserDTO;
import switch2021.project.model.SystemUser.User;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserMapper {


    public OutputUserDTO toDto(User user) {

        String username = user.getUserName().getText();
        String email = user.getSystemUserId().getEmail().getEmailText();
        String function = user.getFunction().getText();
        String photo = user.getPhoto().getExtension();
        String active = user.isActive() ? "True" : "False";

        OutputUserDTO outputUserDTO = new OutputUserDTO(username, email, function, photo, active);

        //Add HATEOAS to OutPut DTO

        //Self Relation
        outputUserDTO.add(linkTo(methodOn(UserController.class).getUser(outputUserDTO.email)).withSelfRel());

        //Collection (list) Relation
        outputUserDTO.add(linkTo(methodOn(UserController.class).showAllUsers()).withRel("Collection"));

        //Delete option
        outputUserDTO.add(linkTo(methodOn(UserController.class).deleteUser(outputUserDTO.email)).withRel("Delete"));

        return outputUserDTO;
    }

    public CollectionModel<OutputUserDTO> toCollectionDTO(List<User> userList) {

        CollectionModel<OutputUserDTO> users = CollectionModel.of(userList.stream()
                .map(user -> toDto(user))
                .collect(Collectors.toList()));

        //HATEOAS

        //Add Self Relation
        users.add(linkTo(methodOn(UserController.class).showAllUsers()).withSelfRel());

        return users;

    }

}
