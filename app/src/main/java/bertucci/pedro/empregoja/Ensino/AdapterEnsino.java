package bertucci.pedro.empregoja.Ensino;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bertucci.pedro.empregoja.Main.MainProfile;
import bertucci.pedro.empregoja.R;
import bertucci.pedro.empregoja.interfaces.RequestInterfaceDeletaEnsino;
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

public class AdapterEnsino extends RecyclerView.Adapter<AdapterEnsino.ViewHolder> {
    private ArrayList<Ensino> ensinos;

    public AdapterEnsino(ArrayList<Ensino> ensinos) {
        this.ensinos = ensinos;
    }

    @Override
    public AdapterEnsino.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final AdapterEnsino.ViewHolder viewHolder, final int i) {
        viewHolder.curso.setText(ensinos.get(i).getAreaEstudo());
        viewHolder.faculdade.setText(ensinos.get(i).getInstituicao());
        viewHolder.tipo.setText(ensinos.get(i).getGrau());

        viewHolder.edita_estudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent myIntent = new Intent(v.getContext(), MainAtualizaEnsino.class);
                myIntent.putExtra("id_estudo",ensinos.get(i).getId_formacaoAcademica()); //Optional parameters
                myIntent.putExtra("nome_instituicao",ensinos.get(i).getInstituicao());
                myIntent.putExtra("grau",ensinos.get(i).getGrau());
                myIntent.putExtra("area_estudo", ensinos.get(i).areaEstudo);
                myIntent.putExtra("ano_inicio", ensinos.get(i).getAnoInicio());
                myIntent.putExtra("ano_final", ensinos.get(i).getAnoFinal());
                myIntent.putExtra("semestre_inicio", ensinos.get(i).getSemestreInicio());
                myIntent.putExtra("semestre_final", ensinos.get(i).getSemestreFinal());
             v.getContext().startActivity(myIntent);
            }
        });


        viewHolder.deleta_estudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
                alertbox.setMessage("Deseja mesmo deletar esse ensino?");
                alertbox.setTitle("Deletando Ensino");
                alertbox.setIcon(R.drawable.lixeira);

                alertbox.setPositiveButton("Sim",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0,
                                                int arg1) {
                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(Constants.BASE_URL)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                RequestInterfaceDeletaEnsino requestInterface = retrofit.create(RequestInterfaceDeletaEnsino.class);

                                final Ensino ensino = new Ensino();
                                ensino.setId_formacaoAcademica(ensinos.get(i).getId_formacaoAcademica());

                                ServerRequest request = new ServerRequest();
                                request.setOperation(Constants.DELETA_ENSINO);
                                request.setEnsino(ensino);
                                Call<ServerResponse> response = requestInterface.operation(request);

                                response.enqueue(new Callback<ServerResponse>() {
                                    @Override
                                    public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                                        ServerResponse resp = response.body();
                                        System.out.println("Ensino Deletada com sucesso");

                                        Bundle bundle = new Bundle();
                                        bundle.putString("parametro", ensinos.get(i).getId_usuario());
                                        Intent myIntent = new Intent(v.getContext(), MainEnsino.class);
                                        myIntent.putExtras(bundle);
                                        v.getContext().startActivity(myIntent);

                                        return;
                                    }

                                    @Override
                                    public void onFailure(Call<ServerResponse> call, Throwable t) {

                                    }
                                });
                            }
                        });
                alertbox.setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0,
                                                    int arg1) {

                                }
                    });
                alertbox.show();

            }
        });


    }


    @Override
    public int getItemCount() {
        return ensinos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView curso, faculdade, tipo;
        private Button edita_estudo, deleta_estudo;

        public ViewHolder(View view) {
            super(view);

            curso = (TextView)view.findViewById(R.id.instituicao_atualiza);
            faculdade = (TextView) view.findViewById(R.id.atualiza_curso);
            tipo = (TextView) view.findViewById(R.id.inicio);
            edita_estudo = (Button) view.findViewById(R.id.btnEditaEstudo);
            deleta_estudo = (Button) view.findViewById(R.id.btnDeletaEstudo);




        }




    }

    private  void deletaEnsino(final View v, String id_formacaoAcademica){

    }
}
