package bertucci.pedro.empregoja.models;

/**
 * Created by pedro on 20/06/17.
 */

public class Vaga {

    private String id_vaga;
    private String cargo_vaga;
    private String salario;
    private String descricao;
    private String nome_fantasia;
    private String nome_cidade;


    public String getId_vaga() {
        return id_vaga;
    }

    public void setId_vaga(String id_vaga) {
        this.id_vaga = id_vaga;
    }

    public String getCargo_vaga() {
        return cargo_vaga;
    }

    public void setCargo_vaga(String cargo_vaga) {
        this.cargo_vaga = cargo_vaga;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    public String getNome_cidade() {
        return nome_cidade;
    }

    public void setNome_cidade(String nome_cidade) {
        this.nome_cidade = nome_cidade;
    }
}
