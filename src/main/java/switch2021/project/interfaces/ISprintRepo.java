package switch2021.project.interfaces;

import switch2021.project.model.Sprint.Sprint;
import java.util.List;
import java.util.Optional;

public interface ISprintRepo {


    /** Find a List Of Sprints. */

    List<Sprint> findAllSprints();

    /** Find a List Of Sprint by ID. */

    Sprint findBySprintID(String id);

    /** Find a List Of All Sprints by Project ID. */

    List<Sprint> findAllSprintsByProjectID(String projectID);

    /** Find the Current Sprint. */

    Sprint findCurrentSprint();

    /** Save Sprint. */

    Optional<Sprint> save(Sprint newSprint);

    /** Delete a Sprint. */

    boolean deleteSprint(Sprint sprint);

}

