package bertucci.pedro.empregoja.models;

/**
 * Created by pedro on 20/06/17.
 */

public class Experiencia {

    private String id_experienciaProfissional;
    private String cargo;
    private String empresa;
    private String descricao;
    private String mesInicio;
    private String anoInicio;
    private String mesFinal;
    private String anoFinal;
    private String id_usuario;

    public String getId_experienciaProfissional() {
        return id_experienciaProfissional;
    }

    public void setId_experienciaProfissional(String id_experienciaProfissional) {
        this.id_experienciaProfissional = id_experienciaProfissional;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMesInicio() {
        return mesInicio;
    }

    public void setMesInicio(String mesInicio) {
        this.mesInicio = mesInicio;
    }

    public String getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(String anoInicio) {
        this.anoInicio = anoInicio;
    }

    public String getMesFinal() {
        return mesFinal;
    }

    public void setMesFinal(String mesFinal) {
        this.mesFinal = mesFinal;
    }

    public String getAnoFinal() {
        return anoFinal;
    }

    public void setAnoFinal(String anoFinal) {
        this.anoFinal = anoFinal;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }
}
