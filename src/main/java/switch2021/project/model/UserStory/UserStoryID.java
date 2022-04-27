package switch2021.project.model.UserStory;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.utils.ValueObject;


@Getter
@EqualsAndHashCode
public class UserStoryID implements ValueObject<UserStoryID> {

    /**
     * Attributes
     **/
    private final ProjectID projectID;
    private final UsTitle usTitle;

    /**
     * Constructor
     **/
    public UserStoryID(ProjectID projectID, UsTitle usTitle) {
        checkUsID(projectID,usTitle);
        this.projectID = projectID;
        this.usTitle = usTitle;
    }

    public UserStoryID(String userStoryIdString) {
        String[] values = userStoryIdString.split("_");// Project_2022_1_As a PO, want to test this string
        this.projectID = new ProjectID(Integer.parseInt(values[2]));
        this.usTitle = new UsTitle(values[3]);
    }


    /**
     * Methods
     **/
    private void checkUsID(ProjectID projectID, UsTitle usTitle) {
        if (projectID.getCode().trim().isEmpty() || usTitle.getTitleUs().trim().isEmpty()){
            throw new IllegalArgumentException("Not valid projectCode and/or US title inserted");
        }
    }


    /**
     * Override
     **/

    @Override
    public String toString() {
        return projectID.getCode() + "_" + usTitle.getTitleUs();
    }

    @Override
    public boolean sameValueAs(UserStoryID other) {
        return false;
    }
}
