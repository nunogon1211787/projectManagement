package switch2021.project.dto;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class OutputUserDTO extends RepresentationModel<OutputUserDTO> {
    public String userName;
    public String email;
    public String function;
    public String photo;
    public String isActive;
}
