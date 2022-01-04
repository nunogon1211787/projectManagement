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
    private List<Typology> arrayTypology = new ArrayList<Typology>();
    private List<BusinessSector> arrayBusinessSector = new ArrayList<BusinessSector>();

    /**
     * Metodo adicionar ProjectStatus
     **/
    public boolean add(ProjectStatus projStat) {
        arrayProjectStatus.add(projStat);
        return true;
    }

    /**
     * Metodo adicionar Customer
     **/
    public boolean add(Customer cust) {
        arrayCustomer.add(cust);
        return true;
    }

    /**
     * Metodo adicionar BusinessSector
     **/
    public boolean add(BusinessSector busSect) {
        arrayBusinessSector.add(busSect);
        return true;
    }

    /**
     * >>>>>TYPOLOGY´S METHODS<<<<<
     **/

    /**
     * Metodo adicionar Typology
     **/
    public boolean add(Typology typo) {
        arrayTypology.add(typo);
        return true;
    }

    /**
     * Create Typology
     **/
    public Typology createTypology(String description) {

        Typology typo = new Typology(description);

        return typo;
    }

    /** Métodos Getter e Setter **/

    public List<ProjectStatus> getArrayProjectStatus() {
        return arrayProjectStatus;
    }

    public List<Customer> getArrayCustomer() {
        return arrayCustomer;
    }

    public List<Typology> getArrayTypology() {
        return arrayTypology;
    }

    public List<BusinessSector> getArrayBusinessSector() {
        return arrayBusinessSector;
    }
}