package bertucci.pedro.empregoja.perfil;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import bertucci.pedro.empregoja.R;
import bertucci.pedro.empregoja.interfaces.RequestInterface;
import bertucci.pedro.empregoja.models.Constants;
import bertucci.pedro.empregoja.models.Ensino;
import bertucci.pedro.empregoja.models.ServerRequest;
import bertucci.pedro.empregoja.models.ServerResponse;
import bertucci.pedro.empregoja.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



/**
 * Created by b_ped on 19/04/2017.
 */

public class ProfileEnsino extends Fragment implements View.OnClickListener {

    private SharedPreferences pref;
    private EditText et_instituicao,et_grau,et_area,et_anoInicio,et_anoFinal,et_semestreInicio,et_semestreFinal;
    private ProgressBar progress;
    private Button btnCadastraEnsino;
    private String unique_id;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        unique_id = getArguments().getString("parametro");
        View view = inflater.inflate(R.layout.fragment_ensino,container,false);
        initViews(view);
        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {





    }

    private void initViews(View view){

        btnCadastraEnsino = (AppCompatButton)view.findViewById(R.id.btnCadastraEnsino);
        et_instituicao = (EditText)view.findViewById(R.id.et_instituicao);
        et_grau = (EditText)view.findViewById(R.id.et_grau);
        et_area =  (EditText)view.findViewById(R.id.et_area);
        et_anoInicio = (EditText)view.findViewById(R.id.et_anoIncio);
        et_semestreInicio = (EditText)view.findViewById(R.id.et_semestreInicio);
        et_anoFinal = (EditText)view.findViewById(R.id.et_anoFinal);
        et_semestreFinal = (EditText)view.findViewById(R.id.et_anoFinal);
        progress = (ProgressBar)view.findViewById(R.id.progress);
        btnCadastraEnsino.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnCadastraEnsino:
                String unique_id = this.unique_id;
                String instituicao = et_instituicao.getText().toString();
                String grau = et_grau.getText().toString();
                String areaEstudo = et_area.getText().toString();
                String semestreInicio = et_semestreInicio.getText().toString();
                String anoInicio = et_anoInicio.getText().toString();
                String semestreFinal = et_semestreFinal.getText().toString();
                String anoFinal = et_anoFinal.getText().toString();

                if(!instituicao.isEmpty() && !grau.isEmpty() && !areaEstudo.isEmpty() && !semestreInicio.isEmpty()
                        && !anoInicio.isEmpty() && !semestreFinal.isEmpty() && !anoFinal.isEmpty()) {

                    if(anoFinal.length() == 4 && anoInicio.length() == 4){
                        registerProcess(unique_id, instituicao,grau,areaEstudo,semestreInicio,anoInicio,semestreFinal,anoFinal);
                    }else{
                        Snackbar.make(getView(), "Preencha o ano início e o ano final corretamente!", Snackbar.LENGTH_LONG).show();
                    }


                } else {

                    Snackbar.make(getView(), "Os campos estão vazios!", Snackbar.LENGTH_LONG).show();
                }

                break;


        }
    }

    private void registerProcess(String unique_id, String instituicao, String grau, String areaEstudo , String semestreInicio, String anoInicio, String semestreFinal, String anoFinal){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        Ensino ensino = new Ensino();
        ensino.setUnique_id(unique_id);
        ensino.setInstituicao(instituicao);
        ensino.setGrau(grau);
        ensino.setAreaEstudo(areaEstudo);
        ensino.setSemestreInicio(semestreInicio);
        ensino.setAnoInicio(anoInicio);
        ensino.setSemestreFinal(semestreFinal);
        ensino.setAnoFinal(anoFinal);


        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.REGISTER_OPERATION_STUDENT);
        request.setEnsino(ensino);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Log.d(Constants.TAG,"Errou");
                Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();


            }
        });
    }
}
