package switch2021.project.controller;

import switch2021.project.dto.TaskEffortDTO;
import switch2021.project.dto.TaskIdDTO;
import switch2021.project.model.Company;
import switch2021.project.stores.TaskList;
import switch2021.project.utils.App;

import java.time.LocalDate;

public class RegisterWorkToTaskController {
    private Company company;
    private TaskIdDTO taskIdDTO;
    private TaskList taskList;


    /**
     * Constructor to UI (with SINGLETON).
     */
    public RegisterWorkToTaskController() {
        this.company = App.getInstance().getCompany();
    }

    /**
     * Constructor to test (without SINGLETON).
     */
    public RegisterWorkToTaskController(Company company) {
        this.company = company;
    }





    public boolean createTaskEffort(TaskEffortDTO taskEffortDTO) {
        this.taskIdDTO = taskEffortDTO.getTaskIdDTO();
        int workHours = taskEffortDTO.getWorkHours();
        int workMinutes = taskEffortDTO.getWorkMinutes();
        LocalDate workDate = taskEffortDTO.getWorkDate();
        String comment = taskEffortDTO.getComment();
        String attachment = taskEffortDTO.getAttachment();
        String email = taskEffortDTO.getEmail();

        int taskId = taskIdDTO.getTaskId();
        return true;
    }
}
