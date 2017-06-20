package bertucci.pedro.empregoja.Empregos;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bertucci.pedro.empregoja.Ensino.ProfileList;
import bertucci.pedro.empregoja.R;

public class MainEmpregos extends AppCompatActivity {
    private String unique_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_empregos);
        setTitle("Meus Empregos");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        unique_id = bundle.getString("parametro");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        initFragment();
    }

    private void initFragment(){
        Fragment fragment;
        Bundle bundle = new Bundle();
        bundle.putString("parametro", unique_id);
        fragment = new EmpregosList();
        fragment.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frame_emprego,fragment);
        ft.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
