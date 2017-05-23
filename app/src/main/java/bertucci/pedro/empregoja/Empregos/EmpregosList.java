package bertucci.pedro.empregoja.Empregos;

import android.app.Fragment;
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

import bertucci.pedro.empregoja.Ensino.AdapterEnsino;
import bertucci.pedro.empregoja.R;

/**
 * Created by pedro on 21/05/17.
 */

public class EmpregosList extends Fragment implements View.OnClickListener {

    private FloatingActionButton btnCadastrar;
    private String unique_id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        unique_id = getArguments().getString("parametro");
        getActivity().setTitle("Meus Empregos");
        View view = inflater.inflate(R.layout.fragment_listaempregos,container,false);
        return view;

    }

    @Override
    public void onClick(View v) {

    }
}
