package bertucci.pedro.empregoja.Empregos;

import android.app.Fragment;
import android.app.FragmentTransaction;
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
    private String id_usuario;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        id_usuario = getArguments().getString("parametro");
        getActivity().setTitle("Meus Empregos");
        View view = inflater.inflate(R.layout.fragment_listaempregos,container,false);
        declaracao(view);
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
