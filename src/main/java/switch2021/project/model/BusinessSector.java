package switch2021.project.model;

public class BusinessSector {



    /**
     * Classe BusinessSector -
     * Atributos da Classe
     **/
    private String description;

    /**
     * Constutor da Classe BusinessSector (Paulo - US005)
     **/

    public BusinessSector(String description) {
        this.description = description;
    }



    /** Metodos Override  para comparar objectos diferentes **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessSector that = (BusinessSector) o;

        return (this.description.equals(that.description));
    }
}
