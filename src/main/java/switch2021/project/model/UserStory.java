package switch2021.project.model;


import lombok.Getter;

@Getter
public class UserStory {


    /**
     * Atributo da classe
     */

    private String projectCode;
    private UserStoryStatus userStoryStatus;
    private String description;
    private int priority;
    private int timeEstimate;
    private long id;

    /**
     * Construtor da classe
     */

    public UserStory(String projectCode, UserStoryStatus userStoryStatus, String description, int priority, int timeEstimate) {
        this.projectCode = projectCode;
        this.userStoryStatus = userStoryStatus;
        this.description = description;
        this.priority = priority;
        this.timeEstimate = timeEstimate;
    }

    /**
     * @return a junção entre o código do projeto e o ID que vai ser gerado automáticamente
     */
    public String getUserStoryStringIdentifier() {
        return projectCode + "-" + id;
    }
}
