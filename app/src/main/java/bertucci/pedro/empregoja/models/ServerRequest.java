package bertucci.pedro.empregoja.models;

/**
 * Created by b_ped on 12/03/2017.
 */

public class ServerRequest {
    private String operation;
    private Usuario usuario;
    private Ensino ensino;
    private Empregos emprego;
    private Experiencia experiencia;
    private Vaga vaga;
    private Curriculos curriculo;
    private Candidatura candidatura;

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
    public void setExperiencia(Experiencia experiencia){ this.experiencia = experiencia;}
    public void setVaga(Vaga vaga){ this.vaga = vaga;}
    public void setCurriculo(Curriculos curriculo){ this.curriculo = curriculo;}
    public void setCandidatura(Candidatura candidatura){ this.candidatura = candidatura;}

}
