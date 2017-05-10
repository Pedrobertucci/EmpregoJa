package bertucci.pedro.empregoja.models;

import android.content.Intent;

/**
 * Created by b_ped on 12/03/2017.
 */

public class User {
    private String name;
    private String sobrenome;
    private String email;
    private Integer sexo;
    private String unique_id;
    private String password;
    private String old_password;
    private String new_password;

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }
    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }
}
