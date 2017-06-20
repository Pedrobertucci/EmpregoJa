package bertucci.pedro.empregoja.Ensino;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public void onBindViewHolder(AdapterEnsino.ViewHolder viewHolder, int i) {
        viewHolder.curso.setText(ensinos.get(i).getAreaEstudo());
        viewHolder.faculdade.setText(ensinos.get(i).getInstituicao());
        viewHolder.tipo.setText(ensinos.get(i).getGrau());
    }

    @Override
    public int getItemCount() {
        return ensinos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView curso, faculdade, tipo;
        public ViewHolder(View view) {
            super(view);

            curso = (TextView)view.findViewById(R.id.cargo);
            faculdade = (TextView) view.findViewById(R.id.empresa);
            tipo = (TextView) view.findViewById(R.id.inicio);

        }
    }
}
