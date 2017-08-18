package bertucci.pedro.empregoja.models;

/**
 * Created by pedro on 16/06/17.
 */

public class Empregos {

    private String id_usuario;
    private String id_vaga;
    private String cargo_vaga;
    private String salario;
    private String descricao;
    private String id_empresa;
    private String nome_fantasia;
    private String foto_perfil;
    private String nome_cidade;
    private String id_premium;
    private Double latitude;
    private Double longitude;

    public String getId_vaga() {
        return id_vaga;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public String getNome_cidade() {
        return nome_cidade;
    }

    public String getId_premium() {
        return id_premium;
    }

    public void setId_premium(String id_premium) {
        this.id_premium = id_premium;
    }

    public void setNome_cidade(String nome_cidade) {
        this.nome_cidade = nome_cidade;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
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

    public String getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(String id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    public String getFoto_perfil() {
        return foto_perfil;
    }

    public void setFoto_perfil(String foto_perfil) {
        this.foto_perfil = foto_perfil;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
