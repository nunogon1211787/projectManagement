package switch2021.project.model;

public class Customer {
    /**
     * Customer Class
     * Class Atributes
     **/
    private int id;
    private String name;
    private String email;

    /**
     * Customer Constructor
     **/

    public Customer(int id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    /**
     * Getter and Setters
     **/

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    /** Override Methods **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer that = (Customer) o;

        return ((this.email.equals(that.email)) && (this.id==that.id)  && (this.name.equals(that.name)));
    }
}
