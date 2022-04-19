package switch2021.project.model.UserStory;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.utils.ValueObject;


@Getter
@EqualsAndHashCode
public class UserStoryId implements ValueObject<UserStoryId> {

    /**
     * Attributes
     **/
    private final ProjectID projectID;
    private final UsTitle usTitle;

    /**
     * Constructor
     **/
    public UserStoryId(ProjectID projectID, UsTitle usTitle) {
        this.projectID = projectID;
        this.usTitle = usTitle;
    }

    public UserStoryId(String userStoryIdString){
        String [] values = userStoryIdString.split("_");// Project_2022_1_As a PO, i want to test this string
        this.projectID = new ProjectID(Integer.parseInt(values[2]));
        this.usTitle = new UsTitle(values[3]);
    }


    /**
     * Override
     **/

    @Override
    public String toString() {
        return projectID.getCode() + "_" +  usTitle.getTitleUs();
    }

    @Override
    public boolean sameValueAs(UserStoryId other) {
        return false;
    }
}
