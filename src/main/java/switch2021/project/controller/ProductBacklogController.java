package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.ProductBacklog;
import switch2021.project.model.Project;
import switch2021.project.model.UserStory;
import switch2021.project.model.dto.UserStoryDto;
import switch2021.project.model.mapper.UserStoryMapper;
import switch2021.project.stores.ProjectStore;
import switch2021.project.utils.App;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductBacklogController {


    private Company company;
    private ProjectStore projectStore;
    private Project project;
    private ProductBacklog productBacklog;
    private List<Project> arrayProject;
    private List<UserStory> userStoryList;

    /**
     * Constructor to UI (with SINGLETON).
     */
    public ProductBacklogController() {
        this.company = App.getInstance().getCompany();
    }

    /**
     * Constructor to test (without SINGLETON).
     */
    public ProductBacklogController(Company company) {
        this.company = company;
    }

    public List<Project> getProjectListByUserEmail(String email){
        this.projectStore = this.company.getProjectStore();
        this.arrayProject = this.projectStore.getProjectListByUserEmail(email);
        return arrayProject;
    }

    public Project getProject(String code){
        this.project = this.company.getProjectStore().getProjectByCode(code);
        return this.project;
    }

    public List<UserStoryDto> getUsSortedByPriority(){
        this.productBacklog = this.project.getProductBacklog();
        this.userStoryList = this.project.getProductBacklog().getUsSortedByPriority();

        LinkedList<UserStoryDto> userStoryDtos = new LinkedList<>();
        for (UserStory userStory: userStoryList) {
            UserStoryDto userStoryDto = UserStoryMapper.convertToDto(userStory);
            userStoryDtos.add(userStoryDto);
        }
        return UserStoryMapper.convertToDto(userStoryList);
        //map - tranformar objecto entrada num objecto saida
    //    return this.userStoryList.stream().map(UserStoryMapper::convertToDto).collect(Collectors.toCollection(LinkedList::new));
        // return this.userStoryList.stream().map(userStory -> {return UserStoryMapper.convertToDto(userStory);}).collect(Collectors.toCollection(LinkedList::new));
    }

}
