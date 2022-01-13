package switch2021.project.model;

import java.util.concurrent.atomic.AtomicInteger;

public class ProjectRoles {

    /**
     * Classe SpecialProfile Atributes
     * Attributes
     **/
    private int id_Profile;
    private String name;
    private String type;



    public ProjectRoles(String name, String type) {
        this.id_Profile = ID_PROFILE_GENERATOR.getAndIncrement();
        this.name = name;
        this.type = type;
    }

    /**
     * Copy Constructor
     */

    public ProjectRoles(ProjectRoles originalProfile) {
        this.id_Profile = originalProfile.id_Profile;
        this.name = originalProfile.name;
        this.type = originalProfile.type;
    }
    /**
     * Profle Constructors
     */
    //Create ID automatically
    private static AtomicInteger ID_PROFILE_GENERATOR = new AtomicInteger();

}
