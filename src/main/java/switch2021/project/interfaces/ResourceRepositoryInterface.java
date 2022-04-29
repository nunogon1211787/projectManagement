package switch2021.project.interfaces;

import switch2021.project.model.Resource.Resource;

public interface ResourceRepositoryInterface/* extends CrudRepository<Object, Long> */{
/*
    /**
     * The repository should be able to find a object using given Identity.
     */
    //Optional<Resource> findByResourceId(ResourceId resId);
/*
    /**
     * Finds all objects from this repository with any parameter.
     */
    //List<Resource> findAllByProjectId(ProjectId projId);


    //List<Resource> findAllBySystemUserId(SystemUserID userId);


    /**
     * Save a object in the list of the repository.
     */
    boolean saveResource(Resource resource);

}
