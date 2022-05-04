package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.OutputTaskDTO;
import switch2021.project.dto.TaskDTO;
import switch2021.project.factoryInterface.TaskFactoryInterface;
import switch2021.project.interfaces.*;
import switch2021.project.mapper.TaskMapperNew;
import switch2021.project.model.Resource.ResourceIDReeng;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.Task.TaskReeng;
import switch2021.project.model.UserStory.UserStory;

@Service
    public class CreateTaskService {

        @Autowired
        public TaskFactoryInterface taskFactoryInterface;

        @Autowired
        public TaskMapperNew taskMapper;

        @Autowired
        public TaskRepositoryInterface taskRepositoryInterface;

        @Autowired
        public TaskContainerID taskContainerID;

        @Autowired
        public ResourceRepositoryInterface resourceRepositoryInterface;

        @Autowired
        public SprintRepositoryInterface sprintRepositoryInterface;

        @Autowired
        public IUserStoryRepo iUserStoryRepo;

        @Autowired
        public CreateTaskService() {
        }

        public OutputTaskDTO createAndSaveTask(TaskDTO taskDTO) {

            ResourceIDReeng resId = resourceRepositoryInterface.findById(taskDTO.responsible).getId();

            TaskContainerID z;

            UserStory y = iUserStoryRepo.findUserStoryById(taskDTO.taskContainerID);
            if(y != null){
                z = y.getUserStoryID();
            }else{
                Sprint x = sprintRepositoryInterface.findSprintById(taskDTO.taskContainerID);
                if(x != null){
                    z = x.getSprintID();
                } else{
                    throw new IllegalArgumentException("ID inválido");
                }
            }

            TaskReeng newTask = taskFactoryInterface.createTask(taskDTO, resId, z);

            taskRepositoryInterface.save(newTask);

            return taskMapper.model2Dto(newTask);
        }


}
