package switch2021.project.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Project {

    /**
     * Atributos da classe Projecto
     **/

    private String code;
    private String name;
    private String description;
    private String customer;
    private String typology;
    private String ProjectStatus;

    private ArrayList<String> businessSector;
    private ArrayList<UserStory> productBacklog;

    private CharSequence startDate;  /** è necessario mudar as datas o mesmo formato(ver qual o melhor)  **/
    private LocalDate endDate;

    private int numberOfSprints;
    private int budget;

    /**
     * Construtor de Projecto
     **/

    public Project(String code, String name, String description, String customer, String typology,
                   ArrayList<String> businessSector, CharSequence startDate, int numberOfSprints, int budget) {

        this.code = code;
        this.name = name;
        this.description = description;
        this.customer = customer;
        this.typology = typology;
        this.ProjectStatus = "Status_0";
        this.businessSector = businessSector;
        this.startDate = startDate;
        this.numberOfSprints = numberOfSprints;
        this.budget = budget;
    }

    /**
     * Método que obtem a data actual no momento do uso do proprio metodo;
     **/

    public void setEndDate() {

        this.endDate = LocalDate.now();
    }

    /** Métodos "Getter" dos atributos **/

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCustomer() {
        return customer;
    }

    public String getTypology() {
        return typology;
    }

    public String getProjectStatus() {
        return ProjectStatus;
    }

    public ArrayList<String> getBusinessSector() {
        return businessSector;
    }

    public CharSequence getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getNumberOfSprints() {
        return numberOfSprints;
    }

    public int getBudget() {
        return budget;
    }

    public ArrayList<UserStory> getProductBacklog() {
        return productBacklog;
    }

    boolean checkUserStoryExists(String description){
        for (UserStory userStory : productBacklog){
            if(userStory.getDescription().trim().equalsIgnoreCase(description.trim())){
                return true;
            }
        }
        return false;
    }

}

