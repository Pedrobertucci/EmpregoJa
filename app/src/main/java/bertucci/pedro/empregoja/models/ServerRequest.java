package bertucci.pedro.empregoja.models;

/**
 * Created by b_ped on 12/03/2017.
 */

public class ServerRequest {
    private String operation;
    private Usuarios usuario;
    private Ensino ensino;

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setUser(Usuarios usuario) {
        this.usuario = usuario;
    }

    public void setEnsino(Ensino ensino){
        this.ensino = ensino;
    }

}
