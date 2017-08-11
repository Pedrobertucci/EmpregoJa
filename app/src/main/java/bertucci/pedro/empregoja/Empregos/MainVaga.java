package bertucci.pedro.empregoja.Empregos;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import bertucci.pedro.empregoja.R;
import bertucci.pedro.empregoja.interfaces.RequestInterfaceDadosVaga;
import bertucci.pedro.empregoja.interfaces.RequestInterfaceEnviaCurriculo;
import bertucci.pedro.empregoja.models.Constants;
import bertucci.pedro.empregoja.models.Curriculos;
import bertucci.pedro.empregoja.models.ServerRequest;
import bertucci.pedro.empregoja.models.ServerResponse;
import bertucci.pedro.empregoja.models.Vaga;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.LENGTH_LONG;

public class MainVaga extends AppCompatActivity {

    private Toolbar toolbar;
    String nome_vaga;
    String id_vaga, id_usuario,id_vagaCurriculo;
    private Button envia_cv;
    private ProgressDialog progress;
    private TextView nome_empresa,nome_cidade, salario,descricao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         setContentView(R.layout.activity_main_vaga);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        Bundle params = getIntent().getExtras();
        id_usuario = params.getString("id_usuario");
        id_vaga = params.getString("id_vaga");

        buscaVaga(id_vaga);



        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        nome_empresa = (TextView) findViewById(R.id.nome_empresa_vaga);
        nome_cidade = (TextView) findViewById(R.id.nome_cidade_vaga);
        salario = (TextView) findViewById(R.id.salario_vaga);
        descricao = (TextView) findViewById(R.id.descricao_vaga);
        envia_cv = (Button) findViewById(R.id.btn_enviaCv);

        envia_cv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                registraCurriculo(v,id_usuario,id_vaga);
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void buscaVaga(String id_vaga){
        progress = new ProgressDialog(this, R.style.styleDialogProgress);
        progress.setTitle("");
        progress.setMessage("Buscando dados da vaga...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterfaceDadosVaga requestInterface = retrofit.create(RequestInterfaceDadosVaga.class);

        Vaga vaga = new Vaga();
        vaga.setId_vaga(id_vaga);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.VAGA);
        request.setVaga(vaga);
        final Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();

                if(resp.getResult().equals(Constants.SUCCESS)){
                    progress.dismiss();
                    toolbar = (Toolbar) findViewById(R.id.toolbar);
                    toolbar.setTitleTextColor(0xFFFFFFFF);
                    toolbar.setTitle(resp.getVaga().getCargo_vaga());
                    setSupportActionBar(toolbar);
                    nome_empresa.setText(resp.getVaga().getNome_fantasia());
                    nome_cidade.setText(resp.getVaga().getNome_cidade());
                    salario.setText(resp.getVaga().getSalario());
                    descricao.setText(resp.getVaga().getDescricao());
                    nome_vaga = resp.getVaga().getCargo_vaga();
                    id_vagaCurriculo = resp.getVaga().getId_vaga();
                }

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                progress.dismiss();
                System.out.println("errou");
            }




        });
    }

    public void registraCurriculo(View view,String id_usuario,String id_vaga){

        progress = new ProgressDialog(view.getContext(), R.style.styleDialogProgress);
        progress.setTitle("");
        progress.setMessage("Enviando seu Curriculo...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterfaceEnviaCurriculo requestInterface = retrofit.create(RequestInterfaceEnviaCurriculo.class);

        Curriculos curriculo = new Curriculos();
        curriculo.setId_usuario(id_usuario);
        curriculo.setId_vaga(id_vaga);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.CURRICULUM);
        request.setCurriculo(curriculo);
        final Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();

                if(resp.getResult().equals(Constants.SUCCESS)){
                    progress.dismiss();

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainVaga.this);
                    alertDialogBuilder.setTitle("Sucesso");
                    alertDialogBuilder.setMessage("parabéns sua candidatura foi enviada com sucesso!").setCancelable(false);
                    alertDialogBuilder.setPositiveButton("Fechar",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();


                }else if(resp.getResult().equals(Constants.ERROR)){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainVaga.this);
                    alertDialogBuilder.setTitle("Ops, Aviso");
                    alertDialogBuilder.setMessage("você já se candidatou a essa vaga").setCancelable(false);
                    alertDialogBuilder.setPositiveButton("Fechar",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                    progress.dismiss();
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
