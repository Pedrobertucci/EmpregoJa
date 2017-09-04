package bertucci.pedro.empregoja.models;

/**
 * Created by pedro on 16/06/17.
 */

public class ServerResponseEmpregos {

    private String result;
    private String message;
    private Empregos emprego;

    public String getResult() {
        return result;
    }
    public String getMessage() {
        return message;
    }
    public Empregos getEmprego() {
        return emprego;
    }


}
