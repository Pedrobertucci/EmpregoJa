package bertucci.pedro.empregoja.models;

/**
 * Created by b_ped on 12/03/2017.
 */

public class ServerResponse {
    private String result;
    private String message;
    private Usuario usuario;
    private Empregos[] emprego;
    private Ensino[] ensino;
    private Experiencia[] experiencia;
    private Vaga vaga;
    private Curriculos curriculo;
    private Candidatura[] candidatura;
    private Ensino ensinoperfil;

    public String getResult() {
        return result;
    }
    public String getMessage() {
        return message;
    }
    public Usuario getUsuario() {return usuario;}
    public Empregos[] getEmprego() {return emprego;}
    public Ensino[] getEnsino(){ return ensino; }
    public Experiencia[] getExperiencia(){return experiencia;}
    public Vaga getVaga(){return vaga;}
    public Curriculos getCurriculo(){return curriculo;}
    public Candidatura[] getCandidatura(){return candidatura;}

}
