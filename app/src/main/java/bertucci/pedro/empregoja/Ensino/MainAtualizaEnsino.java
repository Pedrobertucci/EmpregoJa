package bertucci.pedro.empregoja.Ensino;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import bertucci.pedro.empregoja.R;
import bertucci.pedro.empregoja.interfaces.RequestInterfaceDadosFormacao;
import bertucci.pedro.empregoja.models.Constants;
import bertucci.pedro.empregoja.models.Ensino;
import bertucci.pedro.empregoja.models.ServerRequest;
import bertucci.pedro.empregoja.models.ServerResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainAtualizaEnsino extends AppCompatActivity {

    private ProgressDialog progress;
    private Toolbar toolbar;
    private String selectEnsino,id_formacao, nome_instituicao,grau,area_estudo,ano_inicio,ano_final,semestre_inicio,semestre_final;
    private EditText instituicao_atualiza,atualiza_curso,ano_inicio_atualiza,semestre_inicio_atualiza,ano_final_atualiza,semestre_final_atualiza;
    private FloatingActionButton btnAtualizaEnsino;
    Spinner spinner;

    String[] ensino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_atualiza_ensino);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id_formacao = bundle.getString("id_estudo");
        nome_instituicao = bundle.getString("nome_instituicao");
        grau = bundle.getString("grau");
        area_estudo = bundle.getString("area_estudo");
        ano_inicio = bundle.getString("ano_inicio");
        ano_final = bundle.getString("ano_final");
        semestre_inicio = bundle.getString("semestre_inicio");
        semestre_final = bundle.getString("semestre_inicio");
        ensino = new String[]{
                 grau,
                "Ensino Fundamental",
                "Ensino Medio",
                "Curso",
                "Tecnico",
                "Tecnologo",
                "Graduaçao",
                "Pos-Graduaçao",
                "Mestrado",
                "Doutorado"

        };
        declara();
    }

    public void declara(){

        instituicao_atualiza = (EditText)findViewById(R.id.instituicao_atualiza);
        atualiza_curso = (EditText)findViewById(R.id.atualiza_curso);
        ano_inicio_atualiza = (EditText)findViewById(R.id.ano_inicio_atualiza);
        ano_final_atualiza = (EditText)findViewById(R.id.ano_final_atualiza);
        semestre_final_atualiza = (EditText)findViewById(R.id.semestre_final_atualiza);
        semestre_inicio_atualiza = (EditText)findViewById(R.id.semestre_inicio_atualiza);
        btnAtualizaEnsino = (FloatingActionButton)findViewById(R.id.btnAtualizaEnsino);

        instituicao_atualiza.setText(nome_instituicao);
        atualiza_curso.setText(area_estudo);
        ano_inicio_atualiza.setText(ano_inicio);
        ano_final_atualiza.setText(ano_final);
        semestre_inicio_atualiza.setText(semestre_inicio);
        semestre_final_atualiza.setText(semestre_final);

        spinner = (Spinner) findViewById(R.id.grau_atualiza);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainAtualizaEnsino.this, R.layout.textview, ensino);
        spinner.setSelection(adapter.getPosition(grau));
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = spinner.getSelectedItemPosition();
                        selectEnsino = ensino[position];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }

                }
        );

        btnAtualizaEnsino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvaEnsino();
            }
        });
    }
    public void salvaEnsino(){

        String id_formacao = this.id_formacao;
        String instituicao = instituicao_atualiza.getText().toString();
        String grau = selectEnsino;
        String areaEstudo = atualiza_curso.getText().toString();
        String semestreInicio = semestre_inicio_atualiza.getText().toString();
        String anoInicio = ano_inicio_atualiza.getText().toString();
        String semestreFinal = semestre_final_atualiza.getText().toString();
        String anoFinal = ano_final_atualiza.getText().toString();

       if(!instituicao.isEmpty() && !grau.isEmpty() && !areaEstudo.isEmpty() && !semestreInicio.isEmpty()
                && !anoInicio.isEmpty() && !semestreFinal.isEmpty() && !anoFinal.isEmpty()) {
            if(anoFinal.length() == 4 && anoInicio.length() == 4){
                mandaAtualizacao(id_formacao, instituicao,grau,areaEstudo,semestreInicio,anoInicio,semestreFinal,anoFinal);
           }else{
               Toast.makeText(this, "Preencha o ano início e o ano final corretamente!", Toast.LENGTH_LONG).show();
           }
       } else {

          Toast.makeText(this, "Os campos estão vazios!", Toast.LENGTH_LONG).show();
       }

    }


    private void mandaAtualizacao(String id_formacao,String instituicao,String grau, String areaEstudo, String semestreInicio, String anoInicio, String semestreFinal, String anoFinal){

        progress = new ProgressDialog(this, R.style.styleDialogProgress);
        progress.setTitle("");
        progress.setMessage("Salvando dados do ensino...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterfaceDadosFormacao requestInterface = retrofit.create(RequestInterfaceDadosFormacao.class);

        Ensino ensino = new Ensino();
        ensino.setId_formacaoAcademica(id_formacao);
        ensino.setInstituicao(instituicao);
        ensino.setGrau(grau);
        ensino.setAreaEstudo(areaEstudo);
        ensino.setSemestreInicio(semestreInicio);
        ensino.setSemestreFinal(semestreFinal);
        ensino.setAnoInicio(anoInicio);
        ensino.setAnoFinal(anoFinal);


        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.ALTERA_CURSO);
        request.setEnsino(ensino);
        final Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {
                progress.dismiss();
                ServerResponse resp = response.body();
                Toast.makeText(MainAtualizaEnsino.this, resp.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(MainAtualizaEnsino.this, "Ocorreu um erro inesperado", Toast.LENGTH_LONG).show();
            }
        });


    }


}
