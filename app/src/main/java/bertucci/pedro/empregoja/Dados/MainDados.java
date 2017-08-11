package bertucci.pedro.empregoja.Dados;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;

import bertucci.pedro.empregoja.Empregos.MainVaga;
import bertucci.pedro.empregoja.R;
import bertucci.pedro.empregoja.interfaces.RequestInterfaceAtualizaUser;
import bertucci.pedro.empregoja.interfaces.RequestInterfaceAtualizaUser2;
import bertucci.pedro.empregoja.interfaces.RequestInterfaceDados;
import bertucci.pedro.empregoja.mascaras.Mask;
import bertucci.pedro.empregoja.models.Constants;
import bertucci.pedro.empregoja.models.ServerRequest;
import bertucci.pedro.empregoja.models.ServerResponse;
import bertucci.pedro.empregoja.models.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainDados extends AppCompatActivity {
    private ProgressDialog progress;
    private String id_usuario;
    private EditText nome,sobrenome,email,senha,repete_senha,cidade,pretencaoSalarial,rg,cpf,telefone;
    private ArrayList<Usuario> data;
    private FloatingActionButton btnAtualizar1,btnAtualizar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dados);
        setTitle("Meus Dados");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id_usuario = bundle.getString("parametro");

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        nome = (EditText) findViewById(R.id.nome_usuario);
        sobrenome = (EditText) findViewById(R.id.sobrenome_usuario);
        email = (EditText) findViewById(R.id.email);
        cidade = (EditText) findViewById(R.id.cep);
        pretencaoSalarial = (EditText) findViewById(R.id.salarial);
        rg = (EditText) findViewById(R.id.rg);
        cpf = (EditText) findViewById(R.id.cpf);
        telefone = (EditText) findViewById(R.id.telefone);
        senha = (EditText) findViewById(R.id.senha1);
        repete_senha = (EditText) findViewById(R.id.senha2);
        btnAtualizar1 = (FloatingActionButton) findViewById(R.id.btnAtualizar1);
        btnAtualizar2 = (FloatingActionButton) findViewById(R.id.btnAtualizar2);

        cidade.addTextChangedListener(Mask.insert(Mask.CEP, cidade));
        cpf.addTextChangedListener(Mask.insert(Mask.CPF, cpf));
        listaDados(id_usuario);

        btnAtualizar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mandaAtualizacao();
            }
        });
        btnAtualizar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mandaAtualizacao();
            }
        });


        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec aba1 = tabHost.newTabSpec("Primeira");
        aba1.setContent(R.id.primeiraAba);
        aba1.setIndicator("Dados Usuario");

        TabHost.TabSpec aba2= tabHost.newTabSpec("Segunda");
        aba2.setContent(R.id.segundaAba);
        aba2.setIndicator("Dados Pessoais");


        tabHost.addTab(aba1);
        tabHost.addTab(aba2);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void listaDados(String id_usuario){
        progress = new ProgressDialog(this, R.style.styleDialogProgress);
        progress.setTitle("");
        progress.setMessage("Buscando Dados...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterfaceDados requestInterface = retrofit.create(RequestInterfaceDados.class);

        Usuario usuario = new Usuario();
        usuario.setId_usuario(id_usuario);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.DADOS_USUARIO);
        request.setUser(usuario);
        final Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {


                ServerResponse resp = response.body();
                //resp recebe os dados de retorno e manda para atualizaçao
                if(resp.getResult().equals(Constants.SUCCESS)){

                    progress.dismiss();
                    nome.setText(resp.getUsuario().getNome_usuario());
                    sobrenome.setText(resp.getUsuario().getSobrenome_usuario());
                    email.setText(resp.getUsuario().getEmail_usuario());
                    rg.setText(resp.getUsuario().getRg());
                    cpf.setText(resp.getUsuario().getCpf());
                    telefone.setText(resp.getUsuario().getTelefone());
                    pretencaoSalarial.setText(resp.getUsuario().getPretencao());
                    cidade.setText(resp.getUsuario().getCep());
                }

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                progress.dismiss();
                System.out.println("errou");
            }




        });
    }

    private void mandaAtualizacao(){
    /*
        progress = new ProgressDialog(this, R.style.styleDialogProgress);
        progress.setTitle("");
        progress.setMessage("Atualizando Dados...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();*/




        String name = this.nome.getText().toString();
        String sobrenome = this.sobrenome.getText().toString();
        String email = this.email.getText().toString();
        String password = this.senha.getText().toString();
        String password2 = this.repete_senha.getText().toString();
        String cep = this.cidade.getText().toString();
        String pretencao = this.pretencaoSalarial.getText().toString();
        String rg = this.rg.getText().toString();
        String cpf = this.cpf.getText().toString();
        String telefone = this.telefone.getText().toString();

        if(!name.isEmpty() && !sobrenome.isEmpty() && !email.isEmpty() && !pretencao.isEmpty() && !cep.isEmpty() && !rg.isEmpty() && !cpf.isEmpty() && !telefone.isEmpty()) {
            if (rg.length() == 10) { //tamanho rg
                if (cpf.length() == 14) { // tamanho cpf

                    if(password.length() >=1 || password2.length() >=1){ //valida se tem algo no campo
                        if(password.length() >= 6 && password2.length() >= 6){ // valida o tamanho
                            if(password.equals(password2)){ // valida se sao iguais.


                                atualizaDados(name,sobrenome,email,password,cep,pretencao,rg,cpf,telefone);

                            }else{
                                Toast.makeText(MainDados.this,"Senhas nao sao identicas", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(MainDados.this,"Preencha Senhas com mais de 6 Caracteres", Toast.LENGTH_LONG).show();
                        }
                    }else if(password.length() == 0 && password2.length() == 0){
                        atualizaDados2(name,sobrenome,email,cep,pretencao,rg,cpf,telefone);

                    }else{
                        Toast.makeText(MainDados.this,"Preencha corretamente as Senhas", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(MainDados.this,"Digite CPF por completo!", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(MainDados.this,"Digite RG por completo!", Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(MainDados.this,"Os campos estão vazios!", Toast.LENGTH_LONG).show();
        }




    }

    public void atualizaDados(String name,String sobrenome,String email, String senha,String cep,String pretencao,String rg, String cpf,String telefone){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterfaceAtualizaUser requestInterface = retrofit.create(RequestInterfaceAtualizaUser.class);

        Usuario usuario = new Usuario();
        usuario.setId_usuario(this.id_usuario);
        usuario.setNome_usuario(name);
        usuario.setSobrenome_usuario(sobrenome);
        usuario.setEmail_usuario(email);
        usuario.setSenha_usuario(senha);
        usuario.setCep(cep);
        usuario.setRg(rg);
        usuario.setCpf(cpf);
        usuario.setPretencao(pretencao);
        usuario.setTelefone(telefone);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.ATUALIZA_USUARIO);
        request.setUser(usuario);
        final Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();

               Toast.makeText(MainDados.this, resp.getMessage(), Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(MainDados.this, "Ocorreu um erro inesperado", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void atualizaDados2(String name,String sobrenome,String email, String cep,String pretencao,String rg, String cpf,String telefone){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterfaceAtualizaUser2 requestInterface = retrofit.create(RequestInterfaceAtualizaUser2.class);

        Usuario usuario = new Usuario();
        usuario.setId_usuario(this.id_usuario);
        usuario.setNome_usuario(name);
        usuario.setSobrenome_usuario(sobrenome);
        usuario.setEmail_usuario(email);
        usuario.setCep(cep);
        usuario.setRg(rg);
        usuario.setCpf(cpf);
        usuario.setPretencao(pretencao);
        usuario.setTelefone(telefone);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.ATUALIZA_USUARIO);
        request.setUser(usuario);
        final Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();

                Toast.makeText(MainDados.this, resp.getMessage(), Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(MainDados.this, "Ocorreu um erro inesperado", Toast.LENGTH_LONG).show();

            }
        });
    }
}


