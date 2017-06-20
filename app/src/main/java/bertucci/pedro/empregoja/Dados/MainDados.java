package bertucci.pedro.empregoja.Dados;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TabHost;

import java.util.ArrayList;

import bertucci.pedro.empregoja.R;
import bertucci.pedro.empregoja.interfaces.RequestInterfaceDados;
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
        listaDados(id_usuario);



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


                }

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                progress.dismiss();
                System.out.println("errou");
            }




        });
    }
}

