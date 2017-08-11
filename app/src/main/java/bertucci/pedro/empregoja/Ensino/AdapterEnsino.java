package bertucci.pedro.empregoja.Ensino;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

import bertucci.pedro.empregoja.R;
import bertucci.pedro.empregoja.models.Ensino;

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
             v.getContext().startActivity(myIntent);
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
}
