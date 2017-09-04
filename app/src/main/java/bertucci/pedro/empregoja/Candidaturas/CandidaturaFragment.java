package bertucci.pedro.empregoja.Candidaturas;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import bertucci.pedro.empregoja.Ensino.AdapterEnsino;
import bertucci.pedro.empregoja.R;
import bertucci.pedro.empregoja.interfaces.RequestInterfaceEnsinoList;
import bertucci.pedro.empregoja.interfaces.RequestInterfaceListaCandidaturas;
import bertucci.pedro.empregoja.interfaces.RequestInterfaceListaEmpregos;
import bertucci.pedro.empregoja.models.Candidatura;
import bertucci.pedro.empregoja.models.Constants;
import bertucci.pedro.empregoja.models.Ensino;
import bertucci.pedro.empregoja.models.ServerRequest;
import bertucci.pedro.empregoja.models.ServerResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pedro on 21/06/17.
 */

public class CandidaturaFragment extends Fragment implements View.OnClickListener{

    private String id_usuario;
    private ProgressDialog progress;
    private DataAdapterCandidaturas adapter;
    private ArrayList<Candidatura> data;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        id_usuario = getArguments().getString("parametro");
        View view = inflater.inflate(R.layout.fragment_candidatura,container,false);

        listaCandidaturas(view,id_usuario);
        return view;

    }
    @Override
    public void onClick(View v) {

    }
    private void listaCandidaturas(final View view, String id_usuario){
        progress = new ProgressDialog(getActivity(), R.style.styleDialogProgress);
        progress.setTitle("");
        progress.setMessage("Buscando suas Candidaturas...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterfaceListaCandidaturas requestInterface = retrofit.create(RequestInterfaceListaCandidaturas.class);

        Candidatura candidatura = new Candidatura();
        candidatura.setId_usuario(id_usuario);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.LISTA_CANDIDATURAS);
        request.setCandidatura(candidatura);
        final Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.lista_card_candidatura);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                ServerResponse resp = response.body();



                data = new ArrayList<>(Arrays.asList(resp.getCandidatura()));
                adapter = new DataAdapterCandidaturas(data);
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
}
