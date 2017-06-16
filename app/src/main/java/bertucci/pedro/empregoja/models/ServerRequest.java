package bertucci.pedro.empregoja.models;

/**
 * Created by b_ped on 12/03/2017.
 */

public class ServerRequest {
    private String operation;
    private Usuario usuario;
    private Ensino ensino;
    private Empregos emprego;

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setUser(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setEnsino(Ensino ensino){
        this.ensino = ensino;
    }

    public void setEmprego(Empregos emprego){ this.emprego = emprego;}


}
