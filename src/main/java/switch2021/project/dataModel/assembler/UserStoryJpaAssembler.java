package switch2021.project.dataModel.assembler;

import org.springframework.stereotype.Component;
import switch2021.project.dataModel.jpa.UserStoryJpa;
import switch2021.project.entities.aggregates.UserStory.UserStory;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.UsHour;
import switch2021.project.entities.valueObjects.vos.UsPriority;
import switch2021.project.entities.valueObjects.vos.UserStoryID;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserStoryJpaAssembler {

    public UserStoryJpa toData(UserStory userStory) {
        UserStoryID id = userStory.getUserStoryID();
        int priority = userStory.getPriority().getPriorityUs();
        String description = userStory.getDescription().getText();
        double timeEstimate = userStory.getTimeEstimate().getUsHours();

        String startDate = null;
        UserStoryJpa parentJpa = null;
        String endDate = null;
        String cancelled = null;

        if(!(userStory.getParentUserStory() == null)) {
            parentJpa = toData(userStory.getParentUserStory());
        }
        if (!(userStory.getUsStartDate() == null)) {
            startDate = userStory.getUsStartDate().toString();
        }
        if(!(userStory.getUsEndDate() == null)) {
            endDate = userStory.getUsEndDate().toString();
        }
        if(!(userStory.getUsCancelled() == null)) {
            cancelled = userStory.getUsCancelled().toString();
        }
        return new UserStoryJpa(id, priority, description, timeEstimate, parentJpa, startDate, endDate, cancelled);
    }

    public UserStory toDomain(UserStoryJpa usJpaSaved) {
        UserStoryID id = usJpaSaved.getId();
        UsPriority priority = new UsPriority(usJpaSaved.getPriority());
        Description description = new Description(usJpaSaved.getDescription());
        UsHour timeEstimate = new UsHour(usJpaSaved.getTimeEstimate());

        LocalDate startDate = null;
        UserStory parent = null;
        LocalDate endDate = null;
        LocalDate cancelled = null;

        if(!(usJpaSaved.getParentUserStory() == null)) {
            parent = toDomain(usJpaSaved.getParentUserStory());
        }
        if (!(usJpaSaved.getStartDate() == null)) {
            startDate = LocalDate.parse(usJpaSaved.getStartDate());
        }
        if(!(usJpaSaved.getEndDate() == null)) {
            endDate = LocalDate.parse(usJpaSaved.getEndDate());
        }
        if(!(usJpaSaved.getCancelled() == null)) {
            cancelled = LocalDate.parse(usJpaSaved.getCancelled());
        }
        return new UserStory(id, priority, description, timeEstimate, parent, startDate, endDate, cancelled);
    }

    public List<UserStory> toDomain (List<UserStoryJpa> userStoriesJpa) {
        List<UserStory> productBacklog = new ArrayList<>();

        userStoriesJpa.forEach(usJpa -> productBacklog.add(toDomain(usJpa)));

        return productBacklog;
    }
}
