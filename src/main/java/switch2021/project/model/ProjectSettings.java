package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;

public class ProjectSettings {

    /**
     * Classe ProjectSettings que vai conter as listas -
     * Atributos de ProjectSettings
     **/

    private List<ProjectStatus> arrayProjectStatus;
    private List<Customer> arrayCustomer;
    private List<BusinessSector> arrayBusinessSector;

    /**
     * Initiate lists of class´s Constructor (Paulo - US005 & Ivan)
     **/

    public ProjectSettings() {


        this.arrayBusinessSector = new ArrayList<>();
        this.arrayBusinessSector.add(new BusinessSector("Business_0"));
        this.arrayBusinessSector.add(new BusinessSector("Business_1"));
        this.arrayBusinessSector.add(new BusinessSector("Business_2"));

        this.arrayCustomer = new ArrayList<>();
        this.arrayCustomer.add(new Customer(0, "customer1@email.com"));  // estes parametros são para efeitos de testes
        this.arrayCustomer.add(new Customer(1, "customer2@email.com"));

        this.arrayProjectStatus = new ArrayList<>();
        this.arrayProjectStatus.add(new ProjectStatus("Planned"));
        this.arrayProjectStatus.add(new ProjectStatus("Inception"));
        this.arrayProjectStatus.add(new ProjectStatus("Elaboration"));
        this.arrayProjectStatus.add(new ProjectStatus("Construction"));
        this.arrayProjectStatus.add(new ProjectStatus("Transition"));
        this.arrayProjectStatus.add(new ProjectStatus("Warranty"));
        this.arrayProjectStatus.add(new ProjectStatus("Closed"));
    }

    /**
     * >>>>>PROJECTSTATUS´S METHODS<<<<<
     * <p>
     * Create Project Status
     **/
    public ProjectStatus createProjectStatus(String descritpion) {

        return new ProjectStatus(descritpion);

    }

    /**
     * Metodo adicionar ProjectStatus
     **/
    public boolean add(ProjectStatus projStat) {
        this.arrayProjectStatus.add(projStat);
        return true;
    }

    /**
     * Métodos Getter e Setter
     **/
    public List<ProjectStatus> getArrayProjectStatus() {
        return arrayProjectStatus;
    }

    public ProjectStatus getProjectStatusById(int index) {
        return arrayProjectStatus.get(index);
    }


    /**
     * >>>>>CUSTOMER´S METHODS<<<<<
     * <p>
     * Create Customer
     **/
    public Customer createCustomer(int num, String email) {

        return new Customer(num, email);

    }

    /**
     * Metodo adicionar Customer
     **/
    public boolean add(Customer cust) {
        this.arrayCustomer.add(cust);
        return true;
    }

    /**
     * Métodos Getter e Setter
     **/
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
     * >>>>>BUSINESSSECTOR´S METHODS<<<<<
     * <p>
     * Create Business Sector
     **/
    public BusinessSector createBusinessSector(String descritpion) {

        return new BusinessSector(descritpion);

    }

    /**
     * Metodo adicionar BusinessSector
     **/

    public boolean add(BusinessSector busSect) {
        this.arrayBusinessSector.add(busSect);
        return true;
    }

    /**
     * Metodos getter de BusinessSetcor
     **/
    public List<BusinessSector> getArrayBusinessSector() {
        return arrayBusinessSector;
    }

    public BusinessSector getBussinessSectorById(int index) {
        return arrayBusinessSector.get(index);
    }

}