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

    private List<ProjectStatus> arrayProjectStatus = new ArrayList<ProjectStatus>();
    private List<Customer> arrayCustomer = new ArrayList<Customer>();
    private final List<Typology> arrayTypology = new ArrayList<Typology>();
    private List<BusinessSector> arrayBusinessSector = new ArrayList<BusinessSector>();

    /**
     * Initiate lists of class´s Constructor
     **/

    public ProjectSettings() {
        this.arrayTypology.add(new Typology("Fixed Cost"));
        this.arrayTypology.add(new Typology("Time and Materials"));
    }

    /**
     *       >>>>>PROJECTSTATUS´S METHODS<<<<<
     *
     *
     * Metodo adicionar ProjectStatus
     **/
    public boolean add(ProjectStatus projStat) {
        arrayProjectStatus.add(projStat);
        return true;
    }

    /** Métodos Getter e Setter **/
    public List<ProjectStatus> getArrayProjectStatus() {
        return arrayProjectStatus;
    }

    /**
     *       >>>>>CUSTOMER´S METHODS<<<<<
     *
     *
     * Metodo adicionar Customer
     **/
    public boolean add(Customer cust) {
        arrayCustomer.add(cust);
        return true;
    }

    /** Métodos Getter e Setter **/
    public List<Customer> getArrayCustomer() {
        return arrayCustomer;
    }

    /**
     *       >>>>>TYPOLOGY´S METHODS<<<<<
     *
     *
     *Getter´s Method.**/
    public List<Typology> getArrayTypology() {
        return arrayTypology;
    }

    /**Create Typology.**/
    public Typology createTypology(String description) {

        Typology typo = new Typology(description);

        return typo;
    }

    /**Typology Add´s Method.**/
    public boolean add(Typology typo) {
        arrayTypology.add(typo);
        return true;
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
     *
     * Metodo adicionar BusinessSector
     **/

    public boolean add(BusinessSector busSect) {
        arrayBusinessSector.add(busSect);
        return true;
    }

    public List<BusinessSector> getArrayBusinessSector() {
        return arrayBusinessSector;
    }


}