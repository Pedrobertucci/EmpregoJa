package bertucci.pedro.empregoja.models;

/**
 * Created by b_ped on 12/03/2017.
 */

public class ServerRequest {
    private String operation;
    private User user;
    private Ensino ensino;

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setEnsino(Ensino ensino){
        this.ensino = ensino;
    }
}
