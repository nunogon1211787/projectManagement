package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.interfaces.ProjectRepositoryInterface;
import switch2021.project.mapper.ProjectMapper;
import switch2021.project.model.Project.ProjectReeng;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowAllProjectsService {

   @Autowired
    ProjectRepositoryInterface repo;
    @Autowired
    ProjectMapper map;

    public List<OutputProjectDTO> showAllProjects() {

        List<ProjectReeng> projects = repo.findAll();

        return createProjectDTOList(projects);
    }

    private List<OutputProjectDTO> createProjectDTOList(List<ProjectReeng> projects) {

        List<OutputProjectDTO> allProjectsDto = new ArrayList<>();

        for (ProjectReeng proj : projects) {

            OutputProjectDTO projDto = map.model2Dto(proj);

            allProjectsDto.add(projDto);

        }

        return allProjectsDto;

    }
}
