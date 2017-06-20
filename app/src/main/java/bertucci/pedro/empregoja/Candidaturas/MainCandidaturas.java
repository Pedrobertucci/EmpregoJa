package bertucci.pedro.empregoja.Candidaturas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bertucci.pedro.empregoja.R;

public class MainCandidaturas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_candidaturas);
        setTitle("Minhas Candidaturas");

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
