package switch2021.project.applicationServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.applicationServices.iRepositories.*;
import switch2021.project.dtoModel.dto.OutputTaskDTO;
import switch2021.project.dtoModel.dto.TaskDTO;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.ITaskFactory;
import switch2021.project.dtoModel.mapper.TaskMapperNew;
import switch2021.project.entities.valueObjects.vos.ResourceIDReeng;
import switch2021.project.entities.aggregates.Sprint.Sprint;
import switch2021.project.entities.aggregates.Task.TaskReeng;
import switch2021.project.entities.aggregates.UserStory.UserStory;

import java.time.LocalDate;
import java.util.Optional;

@Service
    public class TaskService {

        @Autowired
        public ITaskFactory ITaskFactory;

        @Autowired
        public TaskMapperNew taskMapper;

        @Autowired
        public TaskRepositoryInterface taskRepositoryInterface;

//        @Autowired
        public IResourceRepo iResourceRepo;

        @Autowired
        public ISprintRepo iSprintRepo;

        @Autowired
        public IUserStoryRepo iUserStoryRepo;

        @Autowired
        public TaskService() {
        }

        public OutputTaskDTO createAndSaveTask(TaskDTO taskDTO) {

            String[] values = taskDTO.responsible.split("_");// user_project_startDate


            UserID sysUserID = new UserID(new Email(values[0]));
            ProjectID projID = new ProjectID(values[1]);
            LocalDate startDate = LocalDate.parse(values[2]);

            ResourceIDReeng resId = new ResourceIDReeng(sysUserID, projID, startDate);

//            ResourceIDReeng resId = iResourceRepo.findById(taskDTO.responsible).getId();

            TaskContainerID taskConId = returnTaskContainerID(taskDTO.taskContainerID);

            TaskReeng newTask = ITaskFactory.createTask(taskDTO, resId, taskConId);

            taskRepositoryInterface.save(newTask);

            return taskMapper.model2Dto(newTask);
        }

        private TaskContainerID returnTaskContainerID(String taskContainerID){
            TaskContainerID z;

            Optional<UserStory> y = iUserStoryRepo.findByUserStoryId(new UserStoryID(taskContainerID));
            if(y.isPresent()){
                z = y.get().getUserStoryID();
            }else{
                Optional<Sprint> x = iSprintRepo.findBySprintID(new SprintID(taskContainerID));
                if(x.isPresent()){
                    z = x.get().getSprintID();
                } else{
                    throw new IllegalArgumentException("ID inválido");
                }
            }
            return z;
        }

}
