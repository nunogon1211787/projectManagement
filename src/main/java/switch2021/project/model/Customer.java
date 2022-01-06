package switch2021.project.model;

public class Customer {



    /**
     * Classe de Customer -
     * Atributos da Classe
     **/
    private int id;
    private String email;

    /**
     * Construtor de  customer (Paulo - US005)
     **/

    public Customer(int id, String email) {
        this.id = id;
        this.email = email;
    }


    /** Metodos Override  para comparar objectos diferentes **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer that = (Customer) o;

        return (this.email.equals(that.email) && this.id==that.id);
    }
}
