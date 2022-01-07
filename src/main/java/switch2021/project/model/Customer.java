package switch2021.project.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Customer {

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(001);
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

    public Customer(String email) {
        this.id =  ID_GENERATOR.getAndIncrement();
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
