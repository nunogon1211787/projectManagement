package switch2021.project.dto.old;

import lombok.Getter;

@Getter

public class SystemUserWithStatusDto {

    /**
     * Attributes
     **/

    private String userName;
    private String email;
    private boolean activateUser;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public SystemUserWithStatusDto(String userName, String email, boolean activateUser) {
        this.userName = userName;
        this.email = email;
        this.activateUser = activateUser;
    }
}