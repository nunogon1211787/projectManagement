package switch2021.project.interfaceAdapters.repositories;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.applicationServices.iRepositories.IResourceRepo;
import switch2021.project.dataModel.assembler.ResourceJpaAssembler;
import switch2021.project.dataModel.jpa.ResourceIDJpa;
import switch2021.project.dataModel.jpa.ResourceJpa;
import switch2021.project.entities.valueObjects.vos.ResourceID;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.UserID;
import switch2021.project.persistence.ResourceJpaRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Repository
public class ResourceRepository implements IResourceRepo {

    @Autowired
    private ResourceJpaRepository jpaRepository;
    @Autowired
    private ResourceJpaAssembler assembler;

    @Override
    public Optional<Resource> findById(ResourceID resourceId) {
        ResourceIDJpa idJpa = new ResourceIDJpa(resourceId.getUser(), resourceId.getProject(),
                resourceId.getStartDate().toString());
        Optional<ResourceJpa> resJpa = jpaRepository.findById(idJpa);
        Optional<Resource> result = Optional.empty();

        if (resJpa.isPresent()) {
            result = Optional.of(assembler.toDomain(resJpa.get()));
        }
        return result;
    }

    @Override
    public List<Resource> findAll() {
        List<ResourceJpa> resourceJpas = jpaRepository.findAll();
        return assembler.toDomain(resourceJpas);
    }

    @Override
    public List<Resource> findAllByProject(ProjectID projectId) {
        List<ResourceJpa> resourceJpas = jpaRepository.findAll();
        List<ResourceJpa> registerProjectResourcesJpa = new ArrayList<>();

        for (ResourceJpa resourceJpa : resourceJpas) {
            if (resourceJpa.getId().getProject().equals(projectId)) {
                registerProjectResourcesJpa.add(resourceJpa);
            }
        }
        return assembler.toDomain(registerProjectResourcesJpa);
    }

    @Override
    public List<Resource> findAllByUser(UserID userId) {
        List<ResourceJpa> systemUserProjectsJpaList = jpaRepository.findAll();
        List<ResourceJpa> systemUserProjectsJpa = new ArrayList<>();

        for (ResourceJpa resourceJpa : systemUserProjectsJpaList) {
            if (resourceJpa.getId().getUser() == userId) {
                systemUserProjectsJpa.add(resourceJpa);
            }
        }
        return assembler.toDomain(systemUserProjectsJpa);
    }

    @Override
    public boolean existsById(ResourceID resourceId) {
        return jpaRepository.existsById(assembler.toData(resourceId));
    }

    @Override
    @Transactional
    public Resource save(Resource newResource) {
        ResourceJpa resJpa = assembler.toData(newResource);

        ResourceJpa resJpaSaved = jpaRepository.save(resJpa);

        return assembler.toDomain(resJpaSaved);
    }

    @Override
    @Transactional
    public boolean deleteByResourceID(ResourceID id) {
        ResourceIDJpa resIdJpa = new ResourceIDJpa(id.getUser(), id.getProject(), id.getStartDate().toString());

        if (jpaRepository.existsById(resIdJpa)) {
            jpaRepository.deleteById(resIdJpa);
            return true;
        }
        return false;
    }
}