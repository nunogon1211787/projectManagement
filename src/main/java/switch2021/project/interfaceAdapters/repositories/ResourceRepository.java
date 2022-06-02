package switch2021.project.interfaceAdapters.repositories;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switch2021.project.applicationServices.iRepositories.IResourceRepo;
import switch2021.project.dataModel.assembler.ResourceJpaAssembler;
import switch2021.project.dataModel.jpa.ResourceIDJpa;
import switch2021.project.dataModel.jpa.ResourceJpa;
import switch2021.project.entities.valueObjects.vos.ResourceIDReeng;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.UserID;
import switch2021.project.persistence.ResourceJpaRepository;

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
    public Optional<Resource> findById(ResourceIDReeng resourceId) {
        ResourceIDJpa idJpa = new ResourceIDJpa(resourceId.getUser(), resourceId.getProject(), resourceId.getStartDate().toString());
        Optional<ResourceJpa> resJpa = jpaRepository.findById(idJpa);
        Optional<Resource> result = Optional.empty();

        if(resJpa.isPresent()){
            result = Optional.of(assembler.toDomain(resJpa.get()));
        }
        return result;
    }

    @Override
    public boolean existsById(ResourceIDReeng resourceId) {
        ResourceIDJpa idJpa = new ResourceIDJpa(resourceId.getUser(), resourceId.getProject(), resourceId.getStartDate().toString());
        Optional<ResourceJpa> resJpa = jpaRepository.findById(idJpa);

        return resJpa.isPresent();
    }

    @Override
    public List<Resource> findAll() {
        List<ResourceJpa> jpaList = jpaRepository.findAll();

        List<Resource> modelList = new ArrayList<>();

        for(ResourceJpa resourceJpa : jpaList){
            Resource res = assembler.toDomain(resourceJpa);
            modelList.add(res);
        }
        return modelList;
    }

    @Override
    public List<Resource> findAllByProject(ProjectID projectId) {
        List<ResourceJpa> projTeamJpaList = jpaRepository.findAll();

        List<Resource> projTeamList = new ArrayList<>();

        for(ResourceJpa resourceJpa : projTeamJpaList){
            if(resourceJpa.getId().getProject() == projectId) {
                Resource res = assembler.toDomain(resourceJpa);
                projTeamList.add(res);
            }
        }
        return projTeamList;
    }

    @Override
    public List<Resource> findAllByUser(UserID userId) {
        List<ResourceJpa> systemUserProjectsJpaList = jpaRepository.findAll();

        List<Resource> systemUserProjectsList = new ArrayList<>();

        for(ResourceJpa resourceJpa : systemUserProjectsJpaList){
            if(resourceJpa.getId().getUser() == userId) {
                Resource res = assembler.toDomain(resourceJpa);
                systemUserProjectsList.add(res);
            }
        }
        return systemUserProjectsList;
    }

    @Override
    public Optional<Resource> save(Resource newResource) {
        ResourceJpa resJpa = assembler.toData(newResource);
        Optional<Resource> resource = Optional.empty();

        if(!jpaRepository.existsById(resJpa.getId())) {
            ResourceJpa resJpaSaved = jpaRepository.save(resJpa);
            resource = Optional.of(assembler.toDomain(resJpaSaved));
        }
        return resource;
    }

    /**
     * Finds all resources
     */

//    @Override
//    public List<UserStory> findAll() {
//
//        List<UserStoryJpa> jpaList = jpaRepository.findAll();
//
//        List<UserStory> modelList = new ArrayList<>();
//
//        jpaList.forEach(usJpa -> modelList.add(assembler.toDomain(usJpa)));
//
//        return modelList;
//    }


}