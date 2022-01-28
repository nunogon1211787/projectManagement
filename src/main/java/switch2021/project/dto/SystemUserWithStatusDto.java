package switch2021.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import switch2021.project.model.Project;

import java.util.ArrayList;
import java.util.List;

@Data

public class SystemUserWithStatusDto {
    private String userName;
    private String email;
    private boolean activateUser;


    /**
     * Constructor to test (without SINGLETON).
     */

    public SystemUserWithStatusDto(String userName, String email, boolean activateUser) {
        this.userName = userName;
        this.email = email;
        this.activateUser = activateUser;
    }
}