package bertucci.pedro.empregoja.models;

/**
 * Created by b_ped on 18/04/2017.
 */

public class Ensino {
    public String id_formacaoAcademica;
    public String instituicao;
    public String grau;
    public String areaEstudo;
    public String semestreInicio;
    public String anoInicio;
    public String semestreFinal;
    public String anoFinal;
    public String id_usuario;


    public String getId_formacaoAcademica() {
        return id_formacaoAcademica;
    }

    public void setId_formacaoAcademica(String id_formacaoAcademica) {
        this.id_formacaoAcademica = id_formacaoAcademica;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getGrau() {
        return grau;
    }

    public void setGrau(String grau) {
        this.grau = grau;
    }

    public String getAreaEstudo() {
        return areaEstudo;
    }

    public void setAreaEstudo(String areaEstudo) {
        this.areaEstudo = areaEstudo;
    }

    public String getSemestreInicio() {
        return semestreInicio;
    }

    public void setSemestreInicio(String semestreInicio) {
        this.semestreInicio = semestreInicio;
    }

    public String getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(String anoInicio) {
        this.anoInicio = anoInicio;
    }

    public String getSemestreFinal() {
        return semestreFinal;
    }

    public void setSemestreFinal(String semestreFinal) {
        this.semestreFinal = semestreFinal;
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

