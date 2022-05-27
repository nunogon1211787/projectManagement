package switch2021.project.interfaces;

import switch2021.project.model.Resource.ResourceIDReeng;
import switch2021.project.model.Resource.ResourceReeng;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.model.valueObject.SystemUserID;


import java.util.List;
import java.util.Optional;

public interface IResourceRepo/* extends CrudRepository<Object, Long> */{

    /**
     * The repository should be able to find a object using given Identity.
     */
    //Optional<Resource> findByResourceId(ResourceId resId);

    Optional<ResourceReeng> findById (ResourceIDReeng resourceId);

    boolean existsById(ResourceIDReeng resourceId);

    /**
     * Finds all objects from this repository with any parameter.
     */

    List<ResourceReeng> findAll();

//    List<ResourceReeng> findByProject(String projectId);

    List<ResourceReeng> findAllByProject(ProjectID projectId);

    List<ResourceReeng> findAllByUser(SystemUserID systemUserId);


    /**
     * Save a object in the list of the repository.
     */
    Optional<ResourceReeng> save(ResourceReeng newResource);

}
