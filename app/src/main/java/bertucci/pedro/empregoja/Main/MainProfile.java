
package bertucci.pedro.empregoja.Main;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import bertucci.pedro.empregoja.Dados.MainDados;
import bertucci.pedro.empregoja.Ensino.MainEnsino;
import bertucci.pedro.empregoja.R;

public class MainProfile extends AppCompatActivity
implements NavigationView.OnNavigationItemSelectedListener {
    private SharedPreferences pref;


    private TextView nome;
    private TextView email;
    private String unique_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setSubtitle("Encontre o seu emprego agora!");
        setSupportActionBar(toolbar);

        Bundle params = getIntent().getExtras();

        String username= params.getString("name");
        String mail = params.getString("email");
        unique_id = params.getString("unique_id");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);

        nome = (TextView)header.findViewById(R.id.tv_nome);
        email = (TextView)header.findViewById(R.id.tv_email);
        nome.setText(username);
        email.setText(mail);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void displaySelectedScreen(int itemId) {


        Fragment fragment = null;


        switch (itemId) {
            case R.id.nav_dados:
                Bundle bundle2 = new Bundle();
                bundle2.putString("parametro", unique_id);
                Intent intent2 = new Intent(this, MainDados.class);
                intent2.putExtras(bundle2);
                startActivity(intent2);
                break;

            case R.id.nav_ensino:
                Bundle bundle = new Bundle();
                bundle.putString("parametro", unique_id);
                Intent intent = new Intent(this, MainEnsino.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            case R.id.nav_candidatura:

                break;

            case R.id.nav_sair:
                logout();
                break;

        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame,fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }

    private void logout() {
        goToLogin();
    }

    private void goToLogin(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
