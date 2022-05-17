package switch2021.project.datamodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import switch2021.project.model.valueObject.UserProfileID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Table(name="profiles")
public class UserProfileJpa {

    /** Attribute **/
    @EmbeddedId
    @Getter
    private UserProfileID userProfileID;

    public UserProfileJpa(UserProfileID userProfileID) {
        this.userProfileID = userProfileID;
    }
}

