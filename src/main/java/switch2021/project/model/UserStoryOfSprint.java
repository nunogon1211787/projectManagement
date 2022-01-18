package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;

public class UserStoryOfSprint {

    /** Class Atribures **/

    private int estimateEffort;
    private UserStory userStoryOfSprint;

    public UserStoryOfSprint(UserStory story, int effort){
        this.estimateEffort = effort;
        this.userStoryOfSprint = story;
    }

    /** Override **/
    @Override
    public boolean equals(Object o) {
        UserStoryOfSprint that = (UserStoryOfSprint) o;
        return (this.estimateEffort == that.estimateEffort)
                && this.userStoryOfSprint.equals(that.userStoryOfSprint);
    }

}
