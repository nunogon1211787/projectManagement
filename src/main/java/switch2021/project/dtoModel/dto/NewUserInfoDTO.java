package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
public class NewUserInfoDTO extends RepresentationModel<NewUserInfoDTO> {

    /**
     * Attributes
     */

    public String userName;
    public String email;
    public String function;
    public String password;
    public String passwordConfirmation;
    public String photo;
}
