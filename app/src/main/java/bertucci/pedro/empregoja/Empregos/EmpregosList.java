package bertucci.pedro.empregoja.Empregos;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import bertucci.pedro.empregoja.Ensino.AdapterEnsino;
import bertucci.pedro.empregoja.R;
import bertucci.pedro.empregoja.interfaces.RequestInterfaceEnsinoList;
import bertucci.pedro.empregoja.interfaces.RequestInterfaceExperienciaList;
import bertucci.pedro.empregoja.models.Constants;
import bertucci.pedro.empregoja.models.Ensino;
import bertucci.pedro.empregoja.models.Experiencia;
import bertucci.pedro.empregoja.models.ServerRequest;
import bertucci.pedro.empregoja.models.ServerResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pedro on 21/05/17.
 */

public class EmpregosList extends Fragment implements View.OnClickListener {

    private FloatingActionButton btnCadastrar;
    private String id_usuario;
    private ProgressDialog progress;
    private AdapterExperiencia adapter;
    private ArrayList<Experiencia> data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        id_usuario = getArguments().getString("parametro");
        getActivity().setTitle("Meus Empregos");
        View view = inflater.inflate(R.layout.fragment_listaempregos,container,false);
        declaracao(view);
        listaExperiencia(view,id_usuario);
        return view;

    }

    public void declaracao(View view){
        btnCadastrar = (FloatingActionButton) view.findViewById(R.id.btnCadastraExperiencia);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileExperiencia();
            }
        });
    }
    private void listaExperiencia(final View view, String id_usuario){
        progress = new ProgressDialog(getActivity(), R.style.styleDialogProgress);
        progress.setTitle("");
        progress.setMessage("Buscando suas Experiencias...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterfaceExperienciaList requestInterface = retrofit.create(RequestInterfaceExperienciaList.class);

        Experiencia experiencia = new Experiencia();
        experiencia.setId_usuario(id_usuario);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.LISTA_EXPERIENCIA);
        request.setExperiencia(experiencia);
        final Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                ServerResponse resp = response.body();



                data = new ArrayList<>(Arrays.asList(resp.getExperiencia()));
                adapter = new AdapterExperiencia(data);
                recyclerView.setAdapter(adapter);

                if(resp.getResult().equals(Constants.SUCCESS)){
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
    @Override
    public void onClick(View v) {

    }

    public void profileExperiencia(){
        Bundle bundle = new Bundle();
        bundle.putString("parametro", this.id_usuario);
        Fragment register = new ProfileEmprego();
        register.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frame_emprego,register);
        ft.addToBackStack(null);
        ft.commit();
    }
}
