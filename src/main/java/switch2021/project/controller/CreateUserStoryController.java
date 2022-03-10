package switch2021.project.controller;

import switch2021.project.dto.ProjectDTO;
import switch2021.project.dto.UserStoryDto;
import switch2021.project.mapper.ProjectsMapper;
import switch2021.project.mapper.UserStoryMapper;
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
    private final UserStoryMapper mapperUS;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public CreateUserStoryController(Company company, ProjectsMapper mapper, UserStoryMapper mapperUS) {
        this.company = company;
        this.mapper = mapper;
        this.mapperUS = mapperUS;
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


    public boolean createUserStory(String code, UserStoryDto createUserStoryDto) {
        this.project = this.company.getProjectStore().getProjectByCode(code);
        ProductBacklog productBacklog = this.project.getProductBacklog();
        UserStory us = project.getProductBacklog().createUserStoryWithDto(createUserStoryDto, this.mapperUS);
        return productBacklog.saveUserStory(us);

    }

}
