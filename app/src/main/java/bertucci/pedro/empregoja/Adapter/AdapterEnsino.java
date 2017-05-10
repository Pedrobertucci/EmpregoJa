package bertucci.pedro.empregoja.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import bertucci.pedro.empregoja.R;

/**
 * Created by b_ped on 19/04/2017.
 */

public class AdapterEnsino extends RecyclerView.Adapter<AdapterEnsino.ViewHolder> {
    private ArrayList<String> countries;

    public AdapterEnsino(ArrayList<String> countries) {
        this.countries = countries;
    }

    @Override
    public AdapterEnsino.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(AdapterEnsino.ViewHolder viewHolder, int i) {
        viewHolder.tv_country.setText(countries.get(i));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_country;
        public ViewHolder(View view) {
            super(view);

            tv_country = (TextView)view.findViewById(R.id.tv_country);
        }
    }
}
