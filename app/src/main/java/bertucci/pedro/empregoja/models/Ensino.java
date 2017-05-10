package bertucci.pedro.empregoja.models;

/**
 * Created by b_ped on 18/04/2017.
 */

public class Ensino {
    public String instituicao;
    public String grau;
    public String areaEstudo;
    public String semestreInicio;
    public String anoInicio;
    public String semestreFinal;
    public String anoFinal;
    public String unique_id;

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

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }
}

