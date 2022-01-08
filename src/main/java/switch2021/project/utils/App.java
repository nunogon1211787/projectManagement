package switch2021.project.utils;

import switch2021.project.model.Company;

/**
 * >>>>>> https://www.devmedia.com.br/padrao-de-projeto-singleton-em-java/26392 || https://www.geeksforgeeks.org/singleton-class-java/ <<<<<<
 **/

public class App {

    private static Company company;

    private static App instance;

    private App() {
        company = new Company();
    }

    public static App getInstance() {

        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    public Company getCompany() {
        return company;
    }
}
