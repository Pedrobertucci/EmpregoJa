package bertucci.pedro.empregoja.Candidaturas;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import bertucci.pedro.empregoja.R;
import bertucci.pedro.empregoja.models.Candidatura;

/**
 * Created by pedro on 21/06/17.
 */

public class DataAdapterCandidaturas extends RecyclerView.Adapter<DataAdapterCandidaturas.ViewHolder> {
    private ArrayList<Candidatura> candidaturas;


    @Override
    public DataAdapterCandidaturas.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_candidaturas, viewGroup, false);
        return new DataAdapterCandidaturas.ViewHolder(view);
    }




    public DataAdapterCandidaturas(ArrayList<Candidatura> candidaturas) {
        this.candidaturas = candidaturas;
    }
    @Override
    public void onBindViewHolder(DataAdapterCandidaturas.ViewHolder viewHolder, int i) {

        viewHolder.cargo.setText(candidaturas.get(i).getCargo_vaga());
        viewHolder.nome_empresa.setText(candidaturas.get(i).getNome_fantasia());
        viewHolder.data.setText(candidaturas.get(i).getData());

    }

    @Override
    public int getItemCount() {
        return candidaturas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nome_vaga, nome_empresa, nome_cidade, data, cargo, finalTrabalho;

        public ViewHolder(View view) {
            super(view);

            cargo = (TextView) view.findViewById(R.id.instituicao_atualiza);
            nome_empresa = (TextView) view.findViewById(R.id.atualiza_curso);
            data = (TextView) view.findViewById(R.id.data);
        }
    }
}
