package switch2021.project.dtoModel.dto;

import org.springframework.hateoas.RepresentationModel;

public class OutputLoginDTO extends RepresentationModel {
    public String email;
    public String username;
    public String token;
}
