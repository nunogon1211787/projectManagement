package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.interfaces.ProjectRepositoryInterface;
import switch2021.project.mapper.ProjectMapper;
import switch2021.project.model.Project.Project;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowAllProjectsService {

    @Autowired
    ProjectRepositoryInterface repo;
    @Autowired
    ProjectMapper map;

    public List<OutputProjectDTO> showAllProjects() {

        List<Project> projects = repo.findAll();

        return createProjectDTOList(projects);
    }

    private List<OutputProjectDTO> createProjectDTOList(List<Project> projects) {

        List<OutputProjectDTO> allProjectsDto = new ArrayList<>();

        for (Project project : projects) {

            OutputProjectDTO projDto = map.model2Dto(project);

            allProjectsDto.add(projDto);

        }

        return allProjectsDto;

    }
}
