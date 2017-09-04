package bertucci.pedro.empregoja.Empregos;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bertucci.pedro.empregoja.R;
import bertucci.pedro.empregoja.models.Constants;
import bertucci.pedro.empregoja.models.Empregos;
import bertucci.pedro.empregoja.models.Usuario;

/**
 * Created by pedro on 14/06/17.
 */

public class DataAdapterEmpregos extends RecyclerView.Adapter<DataAdapterEmpregos.ViewHolder>  implements Filterable {
    private ArrayList<Empregos> emprego;
    private ArrayList<Empregos> ResultEmprego;
    String url= "http://trabalho-ja.tempsite.ws/empresas/upload/";
    String id_usuario;
    public DataAdapterEmpregos(ArrayList<Empregos> empregos, String id_usaurio) {
        this.emprego = empregos;
        this.ResultEmprego = empregos;
        this.id_usuario = id_usaurio;
    }

    @Override
    public DataAdapterEmpregos.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_empregos, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        final Context  context = viewHolder.imagem_perfil.getContext();
        Picasso.with(context).load(url+emprego.get(i).getFoto_perfil()).into(viewHolder.imagem_perfil);
        viewHolder.nome_vaga.setText(emprego.get(i).getCargo_vaga());
        viewHolder.nome_empresa.setText(emprego.get(i).getNome_fantasia());
        viewHolder.nome_cidade.setText(emprego.get(i).getNome_cidade());

        switch (emprego.get(i).getId_premium()){
            case "1":
                viewHolder.candatarme.setTextColor(Color.rgb(124,126,130));
                viewHolder.relativeLayout.setBackgroundColor(Color.rgb(237, 237, 237));
                break;

            case "2":
                viewHolder.candatarme.setTextColor(Color.WHITE);
                viewHolder.premium.setText(Html.fromHtml("&#9733; Vaga Patrocinada"));
                viewHolder.relativeLayout.setBackgroundColor(Color.rgb(229, 99, 83));
                break;

            default:

                viewHolder.candatarme.setTextColor(Color.BLACK);
                break;
        }

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent in = new Intent(context, MainVaga.class);
                Bundle bundle = new Bundle();
                bundle.putString("id_usuario",id_usuario);
                bundle.putString("id_vaga",emprego.get(i).getId_vaga());
                in.putExtras(bundle);
                context.startActivity(in);
            }



        });


    }

    @Override
    public int getItemCount() {
        return emprego.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    ResultEmprego = emprego;
                } else {

                    ArrayList<Empregos> filteredList = new ArrayList<>();

                    for (Empregos androidVersion : emprego) {

                        if (androidVersion.getNome_cidade().toLowerCase().contains(charString) || androidVersion.getNome_fantasia().toLowerCase().contains(charString) || androidVersion.getCargo_vaga().toLowerCase().contains(charString)) {

                            filteredList.add(androidVersion);
                        }
                    }

                    ResultEmprego = filteredList;
                }


                FilterResults filterResults = new FilterResults();
                filterResults.values = ResultEmprego;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                ResultEmprego = (ArrayList<Empregos>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nome_vaga,nome_empresa,nome_cidade, candatarme, premium;
        private ImageView imagem_perfil;
        private RelativeLayout relativeLayout;
        private CardView cardView;

        public ViewHolder(View view) {
            super(view);

            cardView = (CardView) view.findViewById(R.id.card_empregos);
            premium = (TextView) view.findViewById(R.id.txt_premium);
            nome_vaga = (TextView)view.findViewById(R.id.nome_vaga);
            nome_empresa = (TextView) view.findViewById(R.id.nome_empresa);
            nome_cidade = (TextView) view.findViewById(R.id.nome_cidade);
            candatarme = (TextView) view.findViewById(R.id.txtCandidatarMe);
            imagem_perfil = (ImageView) view.findViewById(R.id.imagem_perfil);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.relative2);




        }


    }


}
