package switch2021.project.model;

public class Profile {

    /**
     * Atributos da Classe Profile.
     **/
    private int id;
    private String name;
    private String type;

    /**
     * Getters e Setters.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Método para verificar se o ID recebido corresponde ao ID do objeto.
     */
    public boolean isValidId(int x){ return (x == this.id); }


}
