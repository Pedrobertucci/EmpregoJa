package bertucci.pedro.empregoja.models;

/**
 * Created by pedro on 02/06/17.
 */

public class Usuario {
    private String id_usuario;
    private String nome_usuario;
    private String sobrenome_usuario;
    private String email_usuario;
    private String senha_usuario;
    private String rg;
    private String cpf;
    private String pretencaoSalarial;
    private String telefone;
    private String idade;
    private String Cidade;
    private String sexo;
    private String nascimento;

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

    public String getPretencaoSalarial() {
        return pretencaoSalarial;
    }

    public void setPretencaoSalarial(String pretencaoSalarial) {
        this.pretencaoSalarial = pretencaoSalarial;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getSobrenome_usuario() {
        return sobrenome_usuario;
    }

    public void setSobrenome_usuario(String sobrenome_usuario) {
        this.sobrenome_usuario = sobrenome_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }



    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPretencao() {
        return pretencaoSalarial;
    }

    public void setPretencao(String pretencao) {
        this.pretencaoSalarial = pretencao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
}
