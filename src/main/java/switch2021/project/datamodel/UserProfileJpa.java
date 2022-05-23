package switch2021.project.datamodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import switch2021.project.model.valueObject.UserProfileID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="profiles")
@Getter
public class UserProfileJpa {

    /** Attribute **/
    @EmbeddedId
    private UserProfileID userProfileID;

}

