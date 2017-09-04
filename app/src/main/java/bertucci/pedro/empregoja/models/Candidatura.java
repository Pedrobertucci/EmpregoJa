package bertucci.pedro.empregoja.models;

/**
 * Created by pedro on 21/06/17.
 */

public class Candidatura {
    private String nome_fantasia;
    private String cargo_vaga;
    private String id_usuario;
    private String data;

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }



    public String getCargo_vaga() {
        return cargo_vaga;
    }

    public void setCargo_vaga(String cargo_vaga) {
        this.cargo_vaga = cargo_vaga;
    }
}
