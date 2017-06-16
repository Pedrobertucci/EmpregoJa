package bertucci.pedro.empregoja.models;

/**
 * Created by b_ped on 12/03/2017.
 */

public class ServerResponse {
    private String result;
    private String message;
    private Usuario usuario;
    private Empregos[] emprego;

    public String getResult() {
        return result;
    }
    public String getMessage() {
        return message;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public Empregos[] getEmprego() {return emprego;}



}
