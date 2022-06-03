package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
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
