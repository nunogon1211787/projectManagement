package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;

public class Company {

    /**
     * Classe de Company que contem listas de projectos e users
     * Será necessário adicionar aqui novas listas pertencentes a Company, nomedamente a dos users.
     * Metodos necessarios da class Company
     *      * getListOfProjects()
     *      * getListOfUsers()
     **/


    /**
     * Atrubutos da Classe
     **/
    List<Project> arrayProj = new ArrayList<Project>();

    /**
     * Método para adicionar projectos á lista.
     **/
    public boolean add(Project proj) {
        arrayProj.add(proj);
        return true;
    }

    /**
     * Método para retornar instancia da lista atraves do indice
     **/
    public Project get(int index) {

        return arrayProj.get(index);
    }
}