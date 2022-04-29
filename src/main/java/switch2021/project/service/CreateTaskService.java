package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.OutputTaskDTO;
import switch2021.project.dto.TaskDTO;
import switch2021.project.factoryInterface.TaskFactoryInterface;
import switch2021.project.mapper.TaskMapperNew;
import switch2021.project.model.Task.Task;

@Service
    public class CreateTaskService {

        @Autowired
        public TaskFactoryInterface taskFactoryInterface;

        @Autowired
        public TaskMapperNew taskMapper;

        @Autowired
        public CreateTaskService() {
        }

        public OutputTaskDTO createAndSaveProject(TaskDTO taskDTO) {
            Task newTask = taskFactoryInterface.createTask(

                    taskDTO.name,
                    taskDTO.description,
                    taskDTO.effortEstimate,
                    taskDTO.type,
                    taskDTO.responsible);

            return taskMapper.model2Dto(newTask);
        }


}
