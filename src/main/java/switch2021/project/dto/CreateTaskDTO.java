package switch2021.project.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CreateTaskDTO {


    /**
     * Attributes
     **/
    private final String name;
    private final String description;
    private final int effortEstimate;
    private final String typeName;
    private final String responsible;
    private List<String> precedenceList;

    /**
     * Constructor
     **/

    public CreateTaskDTO(String name, String description, int effortEstimate, String typeName, String responsible) {
        this.name = name;
        this.description = description;
        this.effortEstimate = effortEstimate;
        this.typeName = typeName;
        this.responsible = responsible;
    }

    public CreateTaskDTO(String name, String description, int effortEstimate, String typeName, String responsible, List <String> precedenceList) {
        this.name = name;
        this.description = description;
        this.effortEstimate = effortEstimate;
        this.typeName = typeName;
        this.responsible = responsible;
        this.precedenceList = precedenceList;
    }
}
