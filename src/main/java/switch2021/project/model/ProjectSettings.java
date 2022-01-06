package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;

public class ProjectSettings {

    /**
     * Classe ProjectSettings que vai conter as listas -
     * Atributos de ProjectSettings
     * ** Metodos possivelmente necessarios:
     *      * getProjectStatus()
     *      * getProjectTypology()
     *      * getProjectBusinessSector()
     *      * getProjectCustomer()
     *      * getProjectTypologyByID()
     *      * getProjectBusinessSectorByID
     *      * getCustomerByID()
     *      * getProjectStatusByID()
     **/

    private List<ProjectStatus> arrayProjectStatus;
    private List<Customer> arrayCustomer;
    private List<Typology> arrayTypology;
    private List<BusinessSector> arrayBusinessSector;

    /**
     * Initiate lists of class´s Constructor (Paulo & Ivan)
     **/

    public ProjectSettings() {
        this.arrayTypology = new ArrayList<>();
        this.arrayBusinessSector = new ArrayList<>();
        this.arrayCustomer = new ArrayList<>();
        this.arrayProjectStatus = new ArrayList<>();

        this.arrayTypology.add(new Typology("Fixed Cost"));
        this.arrayTypology.add(new Typology("Time and Materials"));

        this.arrayCustomer.add(new Customer(1, "customer1@email.com"));
        this.arrayCustomer.add(new Customer(2, "customer2@email.com"));

        this.arrayBusinessSector.add(new BusinessSector("Business_0"));
        this.arrayBusinessSector.add(new BusinessSector("Business_1"));
        this.arrayBusinessSector.add(new BusinessSector("Business_2"));

        this.arrayProjectStatus.add(new ProjectStatus("Planned"));
        this.arrayProjectStatus.add(new ProjectStatus("Inception"));
        this.arrayProjectStatus.add(new ProjectStatus("Elaboration"));
        this.arrayProjectStatus.add(new ProjectStatus("Construction"));
        this.arrayProjectStatus.add(new ProjectStatus("Transition"));
        this.arrayProjectStatus.add(new ProjectStatus("Warranty"));
        this.arrayProjectStatus.add(new ProjectStatus("Closed"));
    }

    /**
     *       >>>>>PROJECTSTATUS´S METHODS<<<<<
     *
     * Create Project Status **/
    public ProjectStatus createProjectStatus (String descritpion) {

        return new ProjectStatus(descritpion);

    }

     /**
     * Metodo adicionar ProjectStatus
     **/
    public boolean add(ProjectStatus projStat) {
        this.arrayProjectStatus.add(projStat);
        return true;
    }

    /** Métodos Getter e Setter **/
    public List<ProjectStatus> getArrayProjectStatus() {
        return arrayProjectStatus;
    }
    public ProjectStatus getProjectStatusById(int index) {
        return arrayProjectStatus.get(index);
    }

    /**
     *       >>>>>CUSTOMER´S METHODS<<<<<
     *
     * Create Customer **/
     public Customer createCustomer (int num, String email) {

     return new Customer(num,email);

     }

     /** Metodo adicionar Customer
     **/
    public boolean add(Customer cust) {
        this.arrayCustomer.add(cust);
        return true;
    }

    /** Métodos Getter e Setter **/
    public List<Customer> getArrayCustomer() {
        return arrayCustomer;
    }

    public void setArrayProjectStatus(List<ProjectStatus> arrayProjectStatus) {
        this.arrayProjectStatus = arrayProjectStatus;
    }

    public Customer getCustomerById(int index) {
        return arrayCustomer.get(index);
    }

    /**
     *       >>>>>TYPOLOGY´S METHODS<<<<<
     *
     *Create Typology.**/
    public Typology createTypology(String description) {

        Typology typo = new Typology(description);

        return typo;
    }

    /**Typology Add´s Method.**/
    public boolean add(Typology typo) {
        this.arrayTypology.add(typo);
        return true;
    }

    /**Getter´s Method.**/
    public List<Typology> getArrayTypology() {
        return arrayTypology;
    }

    public Typology getTypologyById(int index) {
        return arrayTypology.get(index);
    }

    /** Typology Validate´s Method**/
    public boolean validateTypology(Typology typo) {
        if(typo == null & this.arrayTypology.contains(typo)) {
            return false;
        }
        return true;
    }

    /** Save Typology at List**/
    public boolean saveTypology(Typology typo){
        if(!validateTypology(typo)) {
            return false;
        }
        return this.arrayTypology.add(typo);
    }

    /**
     *       >>>>>BUSINESSSECTOR´S METHODS<<<<<
     *
     * Create Business Sector **/
    public BusinessSector createBusinessSector (String descritpion) {

        return new BusinessSector(descritpion);

    }

     /** Metodo adicionar BusinessSector
     **/

    public boolean add(BusinessSector busSect) {
        this.arrayBusinessSector.add(busSect);
        return true;
    }

    /** Metodos getter de BusinessSetcor **/
    public List<BusinessSector> getArrayBusinessSector() {
        return arrayBusinessSector;
    }

    public BusinessSector getBussinessSectorById(int index) {
        return arrayBusinessSector.get(index);
    }


}