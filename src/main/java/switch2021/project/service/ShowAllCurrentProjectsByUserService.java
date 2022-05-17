package switch2021.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.datamodel.assembler.ProjectJpaAssembler;
import switch2021.project.dto.DateDTO;
import switch2021.project.dto.IdDTO;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.interfaces.IUserRepo;
import switch2021.project.interfaces.IResourceRepo;
import switch2021.project.mapper.ProjectMapper;
import switch2021.project.model.Project.ProjectReeng;
import switch2021.project.model.Resource.ManageResourcesService;
import switch2021.project.model.Resource.ResourceReeng;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.repositories.ProjectRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowAllCurrentProjectsByUserService {

    @Autowired
    IUserRepo userRepo;
    @Autowired
    IResourceRepo resRepo;
    @Autowired
    ProjectRepository projRepo;
    @Autowired
    ManageResourcesService dsrv;
    @Autowired
    ProjectMapper map;
    @Autowired
    ProjectJpaAssembler assembler;

    public List<OutputProjectDTO> showCurrentProjectsByUser(IdDTO dto, DateDTO dateDto){

        List<OutputProjectDTO> projectsDto = new ArrayList<>();

        if(userRepo.existsByEmail(dto.id)){

            List<ResourceReeng> userResources = resRepo.findAllByUser(dto.id);

            List<ResourceReeng> currentUserResources = dsrv.currentResourcesByDate(userResources, LocalDate.parse(dateDto.date));

            List<ProjectID> resourceProjects = dsrv.listProjectsOfResources(currentUserResources);

            List<ProjectReeng> projects = new ArrayList<>();

            for(ProjectID projId : resourceProjects){

                ProjectReeng proj = projRepo.findById(projId).get();

                projects.add(proj);

            }

            for(ProjectReeng proj : projects){

                OutputProjectDTO projDto = map.model2Dto(proj);

                projectsDto.add(projDto);

            }

        }


        return projectsDto;
    }
}
