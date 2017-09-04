package bertucci.pedro.empregoja.Ensino;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import bertucci.pedro.empregoja.R;

/**
 * Created by pedro on 22/06/17.
 */

public class AtualizaEnsino extends Fragment implements View.OnClickListener {


    private SharedPreferences pref;
    private EditText et_instituicao,et_area,et_anoInicio,et_anoFinal,et_semestreInicio,et_semestreFinal;
    private ProgressBar progress;
    private FloatingActionButton btnCadastraEnsino;
    private String id_usuario, selectEnsino;
    private String nome_instituicao, area,ano_inicio,ano_final,semestreInicio,semestreFinal;
    Spinner spinner;

    String[] ensino = {
            "Selecione o Grau",
            "Ensino Fundamental",
            "Ensino Medio",
            "Curso",
            "Tecnico",
            "Tecnologo",
            "Graduaçao",
            "Pos-Graduaçao",
            "Mestrado",
            "Doutorado"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        id_usuario = getArguments().getString("parametro");
        nome_instituicao = getArguments().getString("nome_instituicao");
        area = getArguments().getString("area");
        ano_inicio = getArguments().getString("ano_inicio");
        ano_final = getArguments().getString("ano_final");
        semestreInicio = getArguments().getString("semestreInicio");
        semestreFinal = getArguments().getString("semestreFinal");

        View view = inflater.inflate(R.layout.fragment_edit_estudo,container,false);

        spinner = (Spinner)view.findViewById(R.id.grau_atualiza);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.textview, ensino);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = spinner.getSelectedItemPosition();
                        selectEnsino = ensino[position];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }

                }
        );


        initViews(view);
        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getActivity().setTitle("Atualizando Ensino");
    }

    private void initViews(View view){

        btnCadastraEnsino = (FloatingActionButton)view.findViewById(R.id.btnCadastraEnsino);

        et_instituicao = (EditText)view.findViewById(R.id.instituicao_atualiza);
        et_area =  (EditText)view.findViewById(R.id.atualiza_curso);
        et_anoInicio = (EditText)view.findViewById(R.id.ano_inicio_atualiza);
        et_semestreInicio = (EditText)view.findViewById(R.id.semestre_inicio_atualiza);
        et_anoFinal = (EditText)view.findViewById(R.id.ano_final_atualiza);
        et_semestreFinal = (EditText)view.findViewById(R.id.ano_final_atualiza);
        progress = (ProgressBar)view.findViewById(R.id.progress);
        btnCadastraEnsino.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

    }
}
