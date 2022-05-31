package switch2021.project.interfaceAdapters.repositories;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.dataModel.jpa.ProjectJpa;
import switch2021.project.applicationServices.iRepositories.IProjectRepo;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.presistence.ProjectJpaRepository;
import switch2021.project.dataModel.assembler.ProjectJpaAssembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Getter
@Setter
public class ProjectRepository implements IProjectRepo {

    @Autowired
    ProjectJpaRepository projectJpaRepository;

    @Autowired
    ProjectJpaAssembler projectJpaAssembler;


    @Override
    public Optional<Project> save(Project newProject) {
        ProjectJpa projectJpa = projectJpaAssembler.toJpaData(newProject);

        ProjectJpa savedProj = projectJpaRepository.save(projectJpa);
        return Optional.of(projectJpaAssembler.toDomain(savedProj));

    }


    @Override
    public Optional<Project> findById(ProjectID id) {
//        ProjectID projID = new ProjectID(id);
        Optional<ProjectJpa> opProjJpa = projectJpaRepository.findById(id.toString());

        if (opProjJpa.isPresent()) {
            ProjectJpa projJpa = opProjJpa.get();

            Project proj = projectJpaAssembler.toDomain(projJpa);
            return Optional.of(proj);
        } else
            return Optional.empty();
    }


    @Override
    public List<Project> findAll() {
        List<ProjectJpa> setProjectJpa = projectJpaRepository.findAll();

        List<Project> setProject = new ArrayList<>();

        for (ProjectJpa projectJpa : setProjectJpa) {
            Project project = projectJpaAssembler.toDomain(projectJpa);
            setProject.add(project);
        }

        return setProject;
    }


    @Override
    public boolean deleteByProjectID(ProjectID id) {

//        ProjectID projID = new ProjectID(id);
        if (projectJpaRepository.existsById(id.toString())) {
            projectJpaRepository.deleteById(id.toString());
            return true;
        }

        return false;
    }


    @Override
    public boolean existsById(ProjectID id) {
        return projectJpaRepository.existsById(id.getCode());
    }

}


