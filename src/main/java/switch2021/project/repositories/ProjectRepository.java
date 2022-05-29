package switch2021.project.repositories;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.datamodel.ProjectJpa;
import switch2021.project.datamodel.ResourceIDJpa;
import switch2021.project.datamodel.ResourceJpa;
import switch2021.project.interfaces.IProjectRepo;
import switch2021.project.model.Project.Project;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.repositories.jpa.ProjectJpaRepository;
import switch2021.project.datamodel.assembler.ProjectJpaAssembler;

import javax.transaction.Transactional;
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

    public Optional<Project> findById(String id) {
        ProjectID projID = new ProjectID(id);
        Optional<ProjectJpa> opProjJpa = projectJpaRepository.findById(projID);

        if (opProjJpa.isPresent()) {
            ProjectJpa projJpa = opProjJpa.get();

            Project proj = projectJpaAssembler.toDomain(projJpa);
            return Optional.of(proj);
        } else
            return Optional.empty();
    }

//    @Override
//    public boolean existsById(ProjectID id) {
//        Optional<ProjectJpa> projJpa = projectJpaRepository.findById(id);
//
//        return projJpa.isPresent();
//
//    }


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
    public boolean deleteByProjectID(String id) {

        ProjectID projID = new ProjectID(id);
        if (projectJpaRepository.existsById(projID)) {
            projectJpaRepository.deleteById(projID);
            return true;
        }

        return false;
    }


//    @Transactional
    public boolean existsById(ProjectID id) {
        return projectJpaRepository.existsById(id);
    }

}
