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
import switch2021.project.model.valueObject.SprintID;
import switch2021.project.model.valueObject.UserStoryID;

import java.util.Optional;

@Service
    public class TaskService {

        @Autowired
        public TaskFactoryInterface taskFactoryInterface;

        @Autowired
        public TaskMapperNew taskMapper;

        @Autowired
        public TaskRepositoryInterface taskRepositoryInterface;

//        @Autowired
        public IResourceRepo IResourceRepo;

        @Autowired
        public ISprintRepo iSprintRepo;

        @Autowired
        public IUserStoryRepo iUserStoryRepo;

        @Autowired
        public TaskService() {
        }

        public OutputTaskDTO createAndSaveTask(TaskDTO taskDTO) {
            ResourceIDReeng resId = IResourceRepo.findById(taskDTO.responsible).getId();

            TaskContainerID taskConId = returnTaskContainerID(taskDTO.taskContainerID);

            TaskReeng newTask = taskFactoryInterface.createTask(taskDTO, resId, taskConId);

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
