package switch2021.project.controller;

import switch2021.project.dto.ProjectDTO;
import switch2021.project.mapper.ProjectsMapper;
import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;
import java.util.Collections;
import java.util.List;


public class CreateUserStoryController {


    /**
     * Attributes
     **/

    private final Company company;
    private Project project;
    private final ProjectsMapper mapper;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public CreateUserStoryController(Company company, ProjectsMapper mapper) {
        this.company = company;
        this.mapper = mapper;
    }

    /**
     * Methods
     **/

    public List<ProjectDTO> getProjectListByUserEmail(String email) {
        ProjectStore projStore = this.company.getProjectStore();
        List<Project> projectListByUser = projStore.getProjectsByUserEmail(email);
        List<ProjectDTO> projectListByUserDtoList = this.mapper.toDtoByUser(projectListByUser);
        return Collections.unmodifiableList(projectListByUserDtoList);
    }


    public boolean createUserStory(String code, String name, int priority, String description, int  estimateEffort) {
        this.project = this.company.getProjectStore().getProjectByCode(code);
        ProductBacklog productBacklog = this.project.getProductBacklog();
        UserStory userStory = productBacklog.createUserStory(name, priority, description, estimateEffort);
        return productBacklog.saveUserStory(userStory);
    }

    public boolean createUserStory1(String code, String name, int priority, String description, int  estimateEffort) {
        this.project = this.company.getProjectStore().getProjectByCode(code);
        ProductBacklog productBacklog = this.project.getProductBacklog();
        UserStory userStory = productBacklog.createUserStory(name, priority, description, estimateEffort);
        return productBacklog.saveUserStory(userStory);
    }

}
