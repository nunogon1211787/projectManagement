package switch2021.project.controller;

import switch2021.project.dto.ProjectDTO;
import switch2021.project.dto.UserStoryDTO;
import switch2021.project.mapper.ProductBacklogMapper;
import switch2021.project.mapper.ProjectsMapper;
import switch2021.project.model.Company;
import switch2021.project.model.Project.Project;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.repositories.ProjectStore;

import java.util.Collections;
import java.util.List;

public class ProductBacklogSortController {

    /**
     * Attributes
     **/
    private final Company company;
    private final ProjectsMapper mapper;
    private final ProductBacklogMapper mapperPB;


    /**
     * Constructor to test (without SINGLETON)
     **/
    public ProductBacklogSortController(Company company, ProjectsMapper mapper, ProductBacklogMapper mapperPB) {
        this.company = company;
        this.mapper = mapper;
        this.mapperPB = mapperPB;
    }


    /**
     * Methods
     * <p>
     * Mutable objects are those whose state can be changed. For instance, an array is mutable, but a String is not.
     * Mutable class members should never be returned to a caller or accepted and stored directly.
     * Doing so leaves you vulnerable to unexpected changes in your class state.
     * Instead use an unmodifiable Collection (via Collections.unmodifiableCollection, Collections.unmodifiableList, ...)
     * or make a copy of the mutable object, and store or return the copy instead.
     **/

    //TODO -----> testar método CDC
    public List<UserStoryDTO> getUsSortedByPriority(String code) {
        Project project = this.company.getProjectStore().findProjectByID(code);
        List<UserStory> userStoryList = project.getUserStoryStore().findUsSortedByPriority();
        List<UserStoryDTO> userStoryListDtoList = this.mapperPB.toDto(userStoryList);
        return Collections.unmodifiableList(userStoryListDtoList);
    }
}
