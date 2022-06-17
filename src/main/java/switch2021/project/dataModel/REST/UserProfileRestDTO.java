package switch2021.project.dataModel.REST;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileRestDTO extends RepresentationModel<UserProfileRestDTO> {

    /**
     * Attribute
     */
    private String description;

}
