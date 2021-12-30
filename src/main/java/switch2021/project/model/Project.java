package switch2021.project.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Project {

    /**
     * Atributos da classe Projecto
     **/
    private char code;

    private String name;
    private String description;
    private String customer;
    private String typology;
    private String ProjectStatus;

    private ArrayList<String> businessSector;

    private LocalDate startDate;
    private LocalDate endDate;
    private int numberOfSprints;
    private int budget;

    /**
     * Construtor de Projecto
     **/
    public Project(char code, String name, String description, String customer, String typology,
                   ArrayList<String> businessSector, CharSequence startDate, int numberOfSprints, int budget) {

        this.code = code;

        this.name = name;
        this.description = description;
        this.customer = customer;
        this.typology = typology;
        this.ProjectStatus = "Status_0";

        this.businessSector = businessSector;

        this.startDate = LocalDate.parse(startDate);
        this.numberOfSprints = numberOfSprints;
        this.budget = budget;
    }

    /**
     * Método que obtem a data actual no momento do uso do proprio metodo;
     **/
    public void setEndDate() {

        this.endDate = LocalDate.now();
    }

}

