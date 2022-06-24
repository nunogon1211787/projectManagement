package switch2021.project.dtoModel.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;
import switch2021.project.applicationServices.iRepositories.TaskContainerID;
import switch2021.project.dtoModel.dto.OutputTaskDTO;
import switch2021.project.entities.aggregates.Task.Task;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.ISprintIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.ITaskIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserStoryIDFactory;
import switch2021.project.entities.valueObjects.vos.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {
    @Autowired
    private ITaskIDFactory taskIDFactory;
    @Autowired
    private ISprintIDFactory sprintIDFactory;
    @Autowired
    private IUserStoryIDFactory userStoryIDFactory;

    public OutputTaskDTO toDto(Task task) {
        OutputTaskDTO outputTaskDTO = new OutputTaskDTO();
        outputTaskDTO.taskID = task.getTaskID().toString();
        outputTaskDTO.taskName = task.getTaskID().getTaskName().getText();

        TaskContainerID sprintOrUsID = task.getTaskID().getTaskContainerID();
        ProjectID projectID;
        UsTitle usTitle;
        Description sprintName;
        if (sprintOrUsID instanceof UserStoryID) {
            projectID = ((UserStoryID) sprintOrUsID).getProjectID();
            usTitle = ((UserStoryID) sprintOrUsID).getUsTitle();
            outputTaskDTO.taskContainerID = usTitle.getTitleUs();
            outputTaskDTO.projectID = projectID.getCode();
        }
        if (sprintOrUsID instanceof SprintID) {
            projectID = ((SprintID) sprintOrUsID).getProjectID();
            sprintName = ((SprintID) sprintOrUsID).getSprintName();
            outputTaskDTO.taskContainerID = sprintName.getText();
            outputTaskDTO.projectID = projectID.getCode();
        }
        outputTaskDTO.resourceID = task.getResponsible().toString();
        outputTaskDTO.userID = task.getResponsible().getUser().getEmail().getEmailText();
        outputTaskDTO.resourceStartDate = task.getResponsible().getStartDate().toString();
        outputTaskDTO.description = task.getDescription().getText();
        outputTaskDTO.type = task.getType().toString();
        outputTaskDTO.effortEstimate = task.getEffortEstimate().getEffortHours();

        return outputTaskDTO;
    }

    public CollectionModel<OutputTaskDTO> toCollectionDto(List<Task> tasks) {

        CollectionModel<OutputTaskDTO> result = CollectionModel.of(tasks.stream()
                .map(this::toDto)
                .collect(Collectors.toList()));
        return result;
    }

    public TaskID stringToId(String taskID) {
        String[] taskContainerIDValues = taskID.split("_");
        String projectID = taskContainerIDValues[0] + "_" + taskContainerIDValues[1] + "_" + taskContainerIDValues[2];
        String sprintNameOrUsTitle = taskContainerIDValues[3];
        String taskDescription = taskContainerIDValues[4];
        TaskContainerID taskContainerID;

        if (!sprintNameOrUsTitle.toUpperCase().startsWith("AS") || (!sprintNameOrUsTitle.contains("want") && !sprintNameOrUsTitle.contains("Want"))) {
            taskContainerID = sprintIDFactory.create(projectID, sprintNameOrUsTitle);
        } else {
            taskContainerID = userStoryIDFactory.create(projectID, sprintNameOrUsTitle);
        }
        return taskIDFactory.createTaskID(taskContainerID, taskDescription);
    }

    private String idToString(TaskID taskID) {
        String result = null;
        TaskContainerID sprintOrUsID = taskID.getTaskContainerID();
        ProjectID projectID;
        UsTitle usTitle;
        Description sprintName;
        if (sprintOrUsID instanceof UserStoryID) {
            projectID = ((UserStoryID) sprintOrUsID).getProjectID();
            usTitle = ((UserStoryID) sprintOrUsID).getUsTitle();
            result = projectID.getCode() + "_" + usTitle.getTitleUs() + "_" + taskID.getTaskName().getText();
        } else if (sprintOrUsID instanceof SprintID) {
            projectID = ((SprintID) sprintOrUsID).getProjectID();
            sprintName = ((SprintID) sprintOrUsID).getSprintName();
            result = projectID.getCode() + "_" + sprintName.getText() + "_" + taskID.getTaskName().getText();
        }
        return result;
    }
}
