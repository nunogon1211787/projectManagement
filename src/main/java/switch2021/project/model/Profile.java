package switch2021.project.model;

public class Profile {

    /**
     * Atributos da Classe Profile.
     **/
    private int id;
    private String name;
    private String type;

    /**
     * Construtores.
     */

    public Profile(int id, String name, String type){
        this.id = id;
        this.name = name;
        this.type = type;
    }

    /**
     * Copy Constructor.
     */

    public Profile(Profile originalProfile){
        this.id = originalProfile.id;
        this.name = originalProfile.name;
        this.type = originalProfile.type;
    }

    /**
     * Getters e Setters.
     */
    public String getName() {
        return this.name;
    }
    public void setName(String newName) { this.name = newName; }

    /**
     * Método para verificar se o ID recebido corresponde ao ID do objeto.
     */
    public boolean isValidId(int x){ return (x == this.id); }


}
