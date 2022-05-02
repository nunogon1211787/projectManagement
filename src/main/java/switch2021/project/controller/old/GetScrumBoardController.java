package switch2021.project.controller.old;

import switch2021.project.dto.old.UserStoryStatusDTO;
import switch2021.project.model.*;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.UserStory.UserStory;

import java.util.Collections;
import java.util.List;

public class GetScrumBoardController {

    /**
     * Attributes
     **/
//    private final Company company;
//    private final ScrumBoardMapper mapper;
    private Sprint sprint;
    private Project project;
    private List<UserStory> userStoryList;


//    /**
//     * Constructor to test (without SINGLETON)
//     **/
//    public GetScrumBoardController(Company company, ScrumBoardMapper mapper) {
//        this.company = company;
//        this.mapper = mapper;
//    }


    /**
     * Getter's
     */
//    public Project getProject(String projectCode) {
//        project = company.getProjectStore().findById(projectCode);
//        return project;
//    }

    public Sprint getCurrentSprint() {
        sprint = project.getCurrentSprint();
        return sprint;
    }

//    public SprintBacklog getSprintBacklog() {
//        sprint.getSprintBacklog();
//        return sprintBacklog;
//    }

    public List<UserStory> getUserStoryList() {
        userStoryList = sprint.getListOfUsFromScrumBoard();
        return Collections.unmodifiableList(userStoryList);
    }


//    /**
//     * Method
//     **/
//    public List<UserStoryStatusDTO> getScrumBoard() {
//        return this.mapper.toDtoList(userStoryList);
//    }

    //Direct Method
   /* public List<ScrumBoardDTO> getScrumBoard2(String projectCode) {
        List<UserStory> list = company.getProjectStore().getProjectByCode(projectCode).getSprints().getCurrentSprint().getSprintBacklog().getUserStoryList();
        return this.mapper.toDtoList(list);
    }*/
}
