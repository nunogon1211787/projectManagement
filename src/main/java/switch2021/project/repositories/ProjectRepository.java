package switch2021.project.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.datamodel.ProjectJpa;
import switch2021.project.model.Project.ProjectReeng;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.repositories.jpa.ProjectJpaRepository;
import switch2021.project.datamodel.assembler.ProjectJpaAssembler;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProjectRepository {

    @Autowired
    ProjectJpaRepository projectJpaRepository;

    @Autowired
    ProjectJpaAssembler projectJpaAssembler;


    public ProjectReeng save(ProjectReeng project) {
        ProjectJpa projectJpa = projectJpaAssembler.toJpaData(project);

        ProjectJpa savedProj = projectJpaRepository.save(projectJpa);

        return projectJpaAssembler.toDomain(savedProj);
    }

    @Transactional
    public Optional<ProjectReeng> findById(ProjectID id) {
        Optional<ProjectJpa> opPersonJpa = projectJpaRepository.findById(id);

        if(opPersonJpa.isPresent()) {
            ProjectJpa personJpa = opPersonJpa.get();

            ProjectReeng person = projectJpaAssembler.toDomain(personJpa);
            return Optional.of(person);
        }
        else
            return Optional.empty();
    }

    public Optional<ProjectReeng> findById(String id) {
        ProjectID id_proj = new ProjectID(id);
        Optional<ProjectJpa> opPersonJpa = projectJpaRepository.findById(id_proj);

        if(opPersonJpa.isPresent()) {
            ProjectJpa personJpa = opPersonJpa.get();

            ProjectReeng person = projectJpaAssembler.toDomain(personJpa);
            return Optional.of(person);
        }
        else
            return Optional.empty();
    }


    @Transactional
    public List<ProjectReeng> findAll() {
        List<ProjectJpa> setProjectJpa = projectJpaRepository.findAll();

        List<ProjectReeng> setProject = new ArrayList<ProjectReeng>();

        for( ProjectJpa projectJpa : setProjectJpa ) {
            ProjectReeng projectReeng = projectJpaAssembler.toDomain(projectJpa);
            setProject.add(projectReeng);
        }

        return setProject;
    }


    @Transactional
    public boolean existsById(ProjectID id) {
        return projectJpaRepository.existsById(id);
    }

}
