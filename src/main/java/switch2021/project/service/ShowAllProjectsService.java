package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.interfaces.IProjectRepo;
import switch2021.project.repositories.ProjectRepository;
import switch2021.project.mapper.ProjectMapper;
import switch2021.project.model.Project.ProjectReeng;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowAllProjectsService {


    @Autowired
    ProjectRepository repo;
    @Autowired ProjectMapper map;


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
