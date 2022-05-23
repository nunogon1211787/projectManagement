package switch2021.project.repositories;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.datamodel.ProjectJpa;
import switch2021.project.interfaces.IProjectRepo;
import switch2021.project.model.Project.ProjectReeng;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.model.valueObject.UserStoryID;
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
    public Optional<ProjectReeng> save(ProjectReeng newProject) {
        ProjectJpa projectJpa = projectJpaAssembler.toJpaData(newProject);


        ProjectJpa savedProj = projectJpaRepository.save(projectJpa);
        return Optional.of(projectJpaAssembler.toDomain(savedProj));


    }

    @Override
    public Optional<ProjectReeng> findById(String id) {
        Optional<ProjectJpa> opProjJpa = projectJpaRepository.findById(id);

        if (opProjJpa.isPresent()) {
            ProjectJpa projJpa = opProjJpa.get();

            ProjectReeng proj = projectJpaAssembler.toDomain(projJpa);
            return Optional.of(proj);
        } else
            return Optional.empty();
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }

    @Override
    public boolean existsByName(String id) {
        return false;
    }

    @Override
    public List<ProjectReeng> findAll() {
        List<ProjectJpa> setProjectJpa = projectJpaRepository.findAll();

        List<ProjectReeng> setProject = new ArrayList<>();

        for (ProjectJpa projectJpa : setProjectJpa) {
            ProjectReeng projectReeng = projectJpaAssembler.toDomain(projectJpa);
            setProject.add(projectReeng);
        }

        return setProject;
    }


    @Override
    public boolean deleteByProjectID(String id) {

        if (projectJpaRepository.existsById(id)) {
            projectJpaRepository.deleteById(id);
            return true;
        }

        return false;
    }


    @Transactional
    public boolean existsById(ProjectID id) {
        return projectJpaRepository.existsById(id.getCode());
    }

}
