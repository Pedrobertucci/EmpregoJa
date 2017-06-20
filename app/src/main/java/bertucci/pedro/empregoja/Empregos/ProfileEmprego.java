package bertucci.pedro.empregoja.Empregos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import bertucci.pedro.empregoja.R;

/**
 * Created by pedro on 20/06/17.
 */

public class ProfileEmprego extends Fragment implements View.OnClickListener {

    private String id_usuario;
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

      /*  btnCadastraEnsino = (FloatingActionButton)view.findViewById(R.id.btnCadastraEnsino);

        et_instituicao = (EditText)view.findViewById(R.id.cargo);
        et_area =  (EditText)view.findViewById(R.id.empresa);
        et_anoInicio = (EditText)view.findViewById(R.id.mes_inicio);
        et_semestreInicio = (EditText)view.findViewById(R.id.ano_inicio);
        et_anoFinal = (EditText)view.findViewById(R.id.mes_final);
        et_semestreFinal = (EditText)view.findViewById(R.id.mes_final);
        progress = (ProgressBar)view.findViewById(R.id.progress);
        btnCadastraEnsino.setOnClickListener(this);*/


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnCadastraEnsino:
            /*
                String id_usuario = this.id_usuario;
                String instituicao = et_instituicao.getText().toString();
                String grau = selectEnsino;
                String areaEstudo = et_area.getText().toString();
                String semestreInicio = et_semestreInicio.getText().toString();
                String anoInicio = et_anoInicio.getText().toString();
                String semestreFinal = et_semestreFinal.getText().toString();
                String anoFinal = et_anoFinal.getText().toString();

                if(!instituicao.isEmpty() && !grau.isEmpty() && !areaEstudo.isEmpty() && !semestreInicio.isEmpty()
                        && !anoInicio.isEmpty() && !semestreFinal.isEmpty() && !anoFinal.isEmpty()) {

                    if(anoFinal.length() == 4 && anoInicio.length() == 4){
                        registerProcess(id_usuario, instituicao,grau,areaEstudo,semestreInicio,anoInicio,semestreFinal,anoFinal);
                    }else{
                        Snackbar.make(getView(), "Preencha o ano início e o ano final corretamente!", Snackbar.LENGTH_LONG).show();
                    }
                } else {

                    Snackbar.make(getView(), "Os campos estão vazios!", Snackbar.LENGTH_LONG).show();
                }
   */
                break;


        }
    }

    private void registerProcess(String id_usuario, String instituicao, String grau, String areaEstudo , String semestreInicio, String anoInicio, String semestreFinal, String anoFinal){
    /*
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterfaceAddEnsino requestInterface = retrofit.create(RequestInterfaceAddEnsino.class);

        Ensino ensino = new Ensino();
        ensino.setInstituicao(instituicao);
        ensino.setGrau(grau);
        ensino.setAreaEstudo(areaEstudo);
        ensino.setSemestreInicio(semestreInicio);
        ensino.setAnoInicio(anoInicio);
        ensino.setSemestreFinal(semestreFinal);
        ensino.setAnoFinal(anoFinal);
        ensino.setId_usuario(id_usuario);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.REGISTER_OPERATION_STUDENT);
        request.setEnsino(ensino);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();



                Toast.makeText(getActivity(),"Ensino Cadastrado com sucesso", Toast.LENGTH_LONG).show();
                getFragmentManager().popBackStack();
                return;
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(getActivity(),"Erro a o cadastrar o Ensino!", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}
