package bertucci.pedro.empregoja.Empregos;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import bertucci.pedro.empregoja.R;
import bertucci.pedro.empregoja.interfaces.RequestInterfaceExperienciaAdd;
import bertucci.pedro.empregoja.models.Constants;
import bertucci.pedro.empregoja.models.Experiencia;
import bertucci.pedro.empregoja.models.ServerRequest;
import bertucci.pedro.empregoja.models.ServerResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pedro on 20/06/17.
 */

public class ProfileEmprego extends Fragment implements View.OnClickListener {

    private String id_usuario;
    private FloatingActionButton btnCadastraExperiencia;
    private EditText cargoEmprego, empresa, mes_inicio, mes_final,ano_final,ano_inicio, descricao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        id_usuario = getArguments().getString("parametro");
        View view = inflater.inflate(R.layout.fragment_experiencia,container,false);
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
        getActivity().setTitle("Cadastrando Experincia");
    }

    private void initViews(View view){

        btnCadastraExperiencia = (FloatingActionButton)view.findViewById(R.id.btnCadastraExperiencia);

        cargoEmprego = (EditText)view.findViewById(R.id.cargo);
        empresa =  (EditText)view.findViewById(R.id.nome_empresa_experiencia);
        mes_inicio = (EditText)view.findViewById(R.id.mes_inicio_experiencia);
        ano_inicio = (EditText)view.findViewById(R.id.ano_inicio_experiencia);
        mes_final = (EditText)view.findViewById(R.id.mes_final_experiencia);
        ano_final = (EditText)view.findViewById(R.id.ano_final_experiencia);
        descricao = (EditText)view.findViewById(R.id.descricao);
        btnCadastraExperiencia.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnCadastraExperiencia:

                String id_usuario = this.id_usuario;
                String cargo = cargoEmprego.getText().toString();
                String empresa = this.empresa.getText().toString();
                String mesInicio = this.mes_inicio.getText().toString();
                String anoInicio = this.ano_inicio.getText().toString();
                String mesFinal = this.mes_final.getText().toString();
                String anoFinal = this.ano_final.getText().toString();
                String descricao = this.descricao.getText().toString();

                if(!cargo.isEmpty() && !empresa.isEmpty() && !mesInicio.isEmpty() && !anoInicio.isEmpty()
                        && !mesFinal.isEmpty() && !anoFinal.isEmpty() && !descricao.isEmpty()) {

                    if(anoFinal.length() == 4 && anoInicio.length() == 4){
                        registerProcess(id_usuario, cargo,empresa,mesInicio,anoInicio,mesFinal,anoFinal,descricao);
                    }else{
                        Snackbar.make(getView(), "Preencha o ano início e o ano final corretamente!", Snackbar.LENGTH_LONG).show();
                    }
                } else {

                    Snackbar.make(getView(), "Os campos estão vazios!", Snackbar.LENGTH_LONG).show();
                }

                break;


        }
    }

    private void registerProcess(String id_usuario, String cargo,String empresa,String mesInicio,String anoInicio,String mesFinal,String anoFinal,String descricao){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterfaceExperienciaAdd requestInterface = retrofit.create(RequestInterfaceExperienciaAdd.class);

        Experiencia experiencia = new Experiencia();
        experiencia.setCargo(cargo);
        experiencia.setEmpresa(empresa);
        experiencia.setMesInicio(mesInicio);
        experiencia.setAnoInicio(anoInicio);
        experiencia.setMesFinal(mesFinal);
        experiencia.setAnoFinal(anoFinal);
        experiencia.setDescricao(descricao);
        experiencia.setId_usuario(this.id_usuario);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.REGISTER_EXPERIENCIA_JOBS);
        request.setExperiencia(experiencia);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                Toast.makeText(getActivity(),"Experiencia cadastrada com sucesso", Toast.LENGTH_LONG).show();
                getFragmentManager().popBackStack();
                return;
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(getActivity(),"Erro a o cadastrar o experiencia!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
