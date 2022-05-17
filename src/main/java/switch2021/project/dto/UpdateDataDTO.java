package switch2021.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@AllArgsConstructor
public class UpdateDataDTO extends RepresentationModel<UpdateDataDTO> {

    /**
     * Attributes
     */

    public String userName;
    public String function;
    public String photo;
    public String oldPassword;
    public String newPassword;

}
