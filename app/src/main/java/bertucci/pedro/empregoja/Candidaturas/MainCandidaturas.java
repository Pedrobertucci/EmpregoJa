package bertucci.pedro.empregoja.Candidaturas;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bertucci.pedro.empregoja.Ensino.ProfileList;
import bertucci.pedro.empregoja.R;

public class MainCandidaturas extends AppCompatActivity {

    private String id_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_candidaturas);
        setTitle("Minhas Candidaturas");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id_usuario = bundle.getString("parametro");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        initFragment();
    }

    private void initFragment(){
        Fragment fragment;
        Bundle bundle = new Bundle();
        bundle.putString("parametro", id_usuario);
        fragment = new CandidaturaFragment();
        fragment.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frame_candidatura,fragment);
        ft.commit();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
