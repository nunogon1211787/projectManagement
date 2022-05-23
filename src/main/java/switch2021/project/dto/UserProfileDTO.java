package switch2021.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UserProfileDTO extends RepresentationModel<UserProfileDTO> {

    /**
     * Attributes
     **/
    public String description;

}