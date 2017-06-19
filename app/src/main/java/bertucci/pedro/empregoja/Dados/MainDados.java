package bertucci.pedro.empregoja.Dados;


import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TabHost;

import bertucci.pedro.empregoja.R;

public class MainDados extends AppCompatActivity {
    private ProgressDialog progress;
    private String id_usuario;
    private EditText nome,sobrenome,email,senha,repete_senha,cidade,pretencaoSalarial,rg,cpf,telefone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dados);
        setTitle("Meus Dados");

        Bundle params = getIntent().getExtras();
        id_usuario = params.getString("id_usuario");


        progress = new ProgressDialog(this, R.style.styleDialogProgress);
        progress.setTitle("");
        progress.setMessage("Buscando Dados...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec aba1 = tabHost.newTabSpec("Primeira");
        aba1.setContent(R.id.primeiraAba);
        aba1.setIndicator("Dados Usuario");

        TabHost.TabSpec aba2= tabHost.newTabSpec("Segunda");
        aba2.setContent(R.id.segundaAba);
        aba2.setIndicator("Dados Pessoais");


        tabHost.addTab(aba1);
        tabHost.addTab(aba2);
    }

}

