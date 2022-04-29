package switch2021.project.model.Task;

import org.junit.jupiter.api.Test;
import switch2021.project.dto.TaskDTO;
import switch2021.project.interfaces.TaskContainerID;
import switch2021.project.interfaces.TaskRepositoryInterface;
import switch2021.project.model.Resource.ResourceId;
import switch2021.project.model.Resource.ResourceReeng;
import switch2021.project.model.Resource.old.Resource;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.valueObject.*;
import switch2021.project.repositories.TaskRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskRepositoryTest {

    @Test
    void createTaskUSTest() {

        UserProfile profile = new UserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);

        ResourceReeng resource = new ResourceReeng(new ResourceId(user.getSystemUserId(), new ProjectID("Project_2022_1")));


        UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As a PO, i want to test this string", 1, "As a PO, i want to test this string - Test", 40);

        TaskContainerID taskContainerID = userStory.getUserStoryID();

        TaskID taskID = new TaskID(taskContainerID, (new Name ("Custodio")));

        TaskReeng task = new TaskReeng(taskID);
        task.setName(new Name("Oscar"));
        task.setDescription(new Description("Isto é uma descrição"));
        task.setEffortEstimate(new EffortEstimate(12));
        task.setType(TaskTypeEnum.Design);
        task.setResponsible(resource.getId());
        task.setTaskContainerID(taskContainerID);

        TaskRepository x = new TaskRepository();
        x.save(task);

        assertEquals("Project_2022_1_As a PO, i want to test this string", task.getTaskContainerID().toString());

    }

//    @Test
//    void createTaskSprint() {
//        UserProfile profile = new UserProfile("Visitor");
//        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
//        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
//        Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));
//
//        Sprint sprint = new Sprint("Project_2022_1", "Project_2022_1_Sprint 1", "Sprint Name");
//
//        TaskContainerID taskContainerID = sprint.getSprintID();
//
//        TaskID taskID = new TaskID(taskContainerID, (new Name ("Custodio")));
//
//        TaskReeng task = new TaskReeng(taskID);
//        task.setName(new Name("Oscar"));
//        task.setDescription(new Description("Isto é uma descrição"));
//        task.setEffortEstimate(new EffortEstimate(12));
//        task.setType(TaskTypeEnum.Design);
//        task.setResponsible(resource);
//        task.setTaskContainerID(taskContainerID);
//
//        TaskRepository x = new TaskRepository();
//        x.save(task);
//
//        assertEquals("Project_2022_1_Sprint 1", task.getTaskContainerID().toString());
//
//    }
}