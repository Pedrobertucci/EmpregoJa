package bertucci.pedro.empregoja.Ensino;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import bertucci.pedro.empregoja.R;
import bertucci.pedro.empregoja.interfaces.RequestInterfaceEnsinoList;
import bertucci.pedro.empregoja.models.Constants;
import bertucci.pedro.empregoja.models.Ensino;
import bertucci.pedro.empregoja.models.ServerRequest;
import bertucci.pedro.empregoja.models.ServerResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by b_ped on 19/04/2017.
 */

public class ProfileList extends Fragment  implements View.OnClickListener{

    private ArrayList escolaridadeList;
    private String id_usuario;
    private FloatingActionButton btnCadastrar;
    private ProgressDialog progress;
    private ArrayList<Ensino> data;
    private AdapterEnsino adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        getActivity().setTitle("Meus Ensinos");

        id_usuario = getArguments().getString("parametro");

        View view = inflater.inflate(R.layout.fragment_listaestudo,container,false);
        declara(view);
        listaEmpregos(view,id_usuario);


        return view;

    }

    public void declara(View v){
        btnCadastrar = (FloatingActionButton) v.findViewById(R.id.btnCadastarEnsino1);
        btnCadastrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCadastarEnsino1:
                profileEnsino();
                break;
        }
    }

    private void listaEmpregos(final View view, String id_usuario){
        progress = new ProgressDialog(getActivity(), R.style.styleDialogProgress);
        progress.setTitle("");
        progress.setMessage("Buscando Ensino...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterfaceEnsinoList requestInterface = retrofit.create(RequestInterfaceEnsinoList.class);

        Ensino ensino = new Ensino();
        ensino.setId_usuario(id_usuario);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.LISTA_ENSINO);
        request.setEnsino(ensino);
        final Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                ServerResponse resp = response.body();



                data = new ArrayList<>(Arrays.asList(resp.getEnsino()));
                adapter = new AdapterEnsino(data);
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

    public void profileEnsino(){
        Bundle bundle = new Bundle();
        bundle.putString("parametro", this.id_usuario);
        Fragment register = new ProfileEnsino();
        register.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frame_ensino,register);
        ft.addToBackStack(null);
        ft.commit();
    }





}
