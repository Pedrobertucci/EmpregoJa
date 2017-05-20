package bertucci.pedro.empregoja.Dados;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

import bertucci.pedro.empregoja.R;

public class MainDados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dados);
        setTitle("Meus Dados");
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

