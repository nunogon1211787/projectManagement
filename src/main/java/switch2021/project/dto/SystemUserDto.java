package switch2021.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
//@AllArgsConstructor

public class SystemUserDto {
    private String userName;
    private String email;
    private boolean activateUser;


    /**
     * Constructor to test (without SINGLETON).
     */

    public SystemUserDto (String userName, String email, boolean activateUser) {
        this.userName = userName;
        this.email=email;
        this.activateUser = activateUser;
    }
}
