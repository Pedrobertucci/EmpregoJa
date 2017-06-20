package bertucci.pedro.empregoja.Empregos;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import bertucci.pedro.empregoja.R;

public class MainVaga extends AppCompatActivity {

    private Toolbar toolbar;
    String nome_vaga = "Nome Vaga";
    String id_vaga;
    private Button envia_cv;
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         setContentView(R.layout.activity_main_vaga);

        Bundle params = getIntent().getExtras();
        id_vaga = params.getString("id_vaga");

        buscaVaga(id_vaga);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setTitle("Nome da Vaga");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        envia_cv = (Button) findViewById(R.id.btn_enviaCv);

        envia_cv.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                progress = new ProgressDialog(v.getContext(), R.style.styleDialogProgress);
                progress.setTitle("");
                progress.setMessage("Enviando seu CV...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.show();
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void buscaVaga(String id_vaga){
        progress = new ProgressDialog(this, R.style.styleDialogProgress);
        progress.setTitle("");
        progress.setMessage("Buscando dados da vaga...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
    }


}
