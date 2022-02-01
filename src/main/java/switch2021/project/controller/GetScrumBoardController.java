package switch2021.project.controller;

import switch2021.project.dto.ScrumBoardDTO;
import switch2021.project.mapper.ScrumBoardMapper;
import switch2021.project.model.Company;
import switch2021.project.model.UserStory;
import java.util.List;

public class GetScrumBoardController {

    private final Company company;
    private final ScrumBoardMapper mapper;

    public GetScrumBoardController(Company company, ScrumBoardMapper mapper) {
        this.company = company;
        this.mapper = mapper;
    }

    public List<ScrumBoardDTO> getScrumBoard(String projectCode){
        List<UserStory> usList = company.getProjectStore().getProjectByCode(projectCode).getCurrentSprint().getSprintBacklog().getUserStoryList();
        return this.mapper.toDtoList(usList);
    }
}
