package switch2021.project.applicationServices.iRepositories;

import switch2021.project.entities.valueObjects.vos.ResourceIDReeng;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.UserID;


import java.util.List;
import java.util.Optional;

public interface IResourceRepo/* extends CrudRepository<Object, Long> */{

    /**
     * The repository should be able to find a object using given Identity.
     */
    //Optional<Resource> findByResourceId(ResourceId resId);

    Optional<Resource> findById (ResourceIDReeng resourceId);

    boolean existsById(ResourceIDReeng resourceId);

    /**
     * Finds all objects from this repository with any parameter.
     */

    List<Resource> findAll();

//    List<ResourceReeng> findByProject(String projectId);

    List<Resource> findAllByProject(ProjectID projectId);

    List<Resource> findAllByUser(UserID userId);


    /**
     * Save a object in the list of the repository.
     */
    Optional<Resource> save(Resource newResource);

}
