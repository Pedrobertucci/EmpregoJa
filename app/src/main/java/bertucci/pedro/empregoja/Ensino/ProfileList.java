package bertucci.pedro.empregoja.Ensino;

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
import android.widget.Button;

import java.util.ArrayList;

import bertucci.pedro.empregoja.R;

/**
 * Created by b_ped on 19/04/2017.
 */

public class ProfileList extends Fragment  implements View.OnClickListener{

    private ArrayList escolaridadeList;
    private String unique_id;
    private FloatingActionButton btnCadastrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        unique_id = getArguments().getString("parametro");

        View view = inflater.inflate(R.layout.fragment_listaestudo,container,false);



        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        escolaridadeList = new ArrayList<>();
        escolaridadeList.add("Ensino 1");
        escolaridadeList.add("Ensino 2");
        declara(view);

        RecyclerView.Adapter adapter = new AdapterEnsino(escolaridadeList);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerView.OnItemTouchListener() {
                    GestureDetector gestureDetector = new GestureDetector(getActivity().getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                        @Override
                        public boolean onSingleTapUp(MotionEvent e) {
                            return true;
                        }

                    });

                    @Override
                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                        View child = rv.findChildViewUnder(e.getX(), e.getY());
                        if (child != null && gestureDetector.onTouchEvent(e)) {
                            int position = rv.getChildAdapterPosition(child);

                        }
                        return false;
                    }

                    @Override
                    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

                    }

                    @Override
                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                    }

                });



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


    public void profileEnsino(){
        Bundle bundle = new Bundle();
        bundle.putString("parametro", this.unique_id);
        Fragment register = new ProfileEnsino();
        register.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frame_ensino,register);
        ft.addToBackStack(null);
        ft.commit();
    }





}
