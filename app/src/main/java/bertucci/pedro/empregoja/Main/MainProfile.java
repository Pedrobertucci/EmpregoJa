
package bertucci.pedro.empregoja.Main;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import bertucci.pedro.empregoja.Candidaturas.MainCandidaturas;
import bertucci.pedro.empregoja.Dados.MainDados;
import bertucci.pedro.empregoja.Empregos.DataAdapterEmpregos;
import bertucci.pedro.empregoja.Empregos.MainEmpregos;
import bertucci.pedro.empregoja.Ensino.MainEnsino;
import bertucci.pedro.empregoja.Manifest;
import bertucci.pedro.empregoja.R;
import bertucci.pedro.empregoja.interfaces.RequestInterfaceListaEmpregos;
import bertucci.pedro.empregoja.models.Constants;
import bertucci.pedro.empregoja.models.Empregos;
import bertucci.pedro.empregoja.models.ServerRequest;
import bertucci.pedro.empregoja.models.ServerResponse;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static bertucci.pedro.empregoja.Login.LoginFragment.USUARIO_CURRICULO;

public class MainProfile extends AppCompatActivity
implements NavigationView.OnNavigationItemSelectedListener  {
    private SharedPreferences pref;
    private ArrayList<Empregos> data;
    private ArrayList empregosList;
    private TextView nome;
    private TextView email;
    private String id_usuario;
    private ProgressDialog progress;
    private DataAdapterEmpregos adapter;
    private RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    LocationManager locationManager;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setSubtitle("Encontre o seu emprego agora!");
        setSupportActionBar(toolbar);

        Bundle params = getIntent().getExtras();

        String username= params.getString("nome");
        String mail = params.getString("email");
        id_usuario = params.getString("id_usuario");
        listaEmpregos(id_usuario);
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swifeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {


            @Override
            public void onRefresh() {
                atualizaEmprego(id_usuario);
            }
        });




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


        permission();


    }

    public void permission(){
        if (checkPermission()) {
            Toast.makeText(this,"Permission already granted.",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,"Please request permission.",Toast.LENGTH_LONG).show();
        }
        if (!checkPermission()){
            requestPermission();
        }else {
            Toast.makeText(this,"Permission already granted.",Toast.LENGTH_LONG).show();
        }

    }

    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED){

            return true;

        } else {

            return false;

        }
    }

    private void requestPermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(getApplicationContext(),"GPS permission allows us to access location data. Please allow in App Settings for additional functionality.",Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Snackbar.make(view,"Permission Granted, Now you can access location data.",Snackbar.LENGTH_LONG).show();

                } else {

                    Snackbar.make(view,"Permission Denied, You cannot access location data.",Snackbar.LENGTH_LONG).show();

                }
                break;
        }
    }

    public void getLocation() {

        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Toast.makeText(this,"Latitude: " + loc.getLatitude() + "Longitude: " + loc.getLongitude(),Toast.LENGTH_LONG).show();
    }


    private void listaEmpregos(String id_usuario){
        progress = new ProgressDialog(this, R.style.styleDialogProgress);
        progress.setTitle("");
        progress.setMessage("Buscando Empregos...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterfaceListaEmpregos requestInterface = retrofit.create(RequestInterfaceListaEmpregos.class);

        Empregos emprego = new Empregos();
        emprego.setId_usuario(id_usuario);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.LISTA_EMPREGOS);
        request.setEmprego(emprego);
        final Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_emprego);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                ServerResponse resp = response.body();
                data = new ArrayList<>(Arrays.asList(resp.getEmprego()));
                adapter = new DataAdapterEmpregos(data,resp.getMessage());
                recyclerView.setAdapter(adapter);

                if(resp.getResult().equals(Constants.SUCCESS)){
                progress.dismiss();

                }

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                progress.dismiss();
                System.out.println("errou");
            }




        });
    }

    private void atualizaEmprego(String id_usuario){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterfaceListaEmpregos requestInterface = retrofit.create(RequestInterfaceListaEmpregos.class);

        Empregos emprego = new Empregos();
        emprego.setId_usuario(id_usuario);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.LISTA_EMPREGOS);
        request.setEmprego(emprego);
        final Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_emprego);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                ServerResponse resp = response.body();
                data = new ArrayList<>(Arrays.asList(resp.getEmprego()));
                adapter = new DataAdapterEmpregos(data,resp.getMessage());
                recyclerView.setAdapter(adapter);

                if(resp.getResult().equals(Constants.SUCCESS)){
                    progress.dismiss();

                }
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                progress.dismiss();
                System.out.println("errou");
            }




        });
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
        if (id == R.id.id_criadores) {
            Context context = getApplicationContext();
            TextView textView = null;
            textView.setText(R.string.suporte);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainProfile.this);
            alertDialogBuilder.setTitle("Suporte Aplicativo");
            alertDialogBuilder.setMessage(Html.fromHtml(String.valueOf(textView))).setCancelable(false);
            alertDialogBuilder.setPositiveButton("Fechar",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void displaySelectedScreen(int itemId) {


        Fragment fragment = null;


        switch (itemId) {
            case R.id.nav_dados:
                Bundle bundle2 = new Bundle();
                bundle2.putString("parametro", id_usuario);
                Intent intent2 = new Intent(this, MainDados.class);
                intent2.putExtras(bundle2);
                startActivity(intent2);
                break;

            case R.id.nav_ensino:
                Bundle bundle = new Bundle();
                bundle.putString("parametro", id_usuario);
                Intent intent = new Intent(this, MainEnsino.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            case R.id.nav_empregos:
                Bundle bundle3 = new Bundle();
                bundle3.putString("parametro", id_usuario);
                Intent intent3 = new Intent(this, MainEmpregos.class);
                intent3.putExtras(bundle3);
                startActivity(intent3);
                break;

            case R.id.nav_candidatura:
                Bundle bundle4 = new Bundle();
                bundle4.putString("parametro", id_usuario);
                Intent intent4 = new Intent(this, MainCandidaturas.class);
                intent4.putExtras(bundle4);
                startActivity(intent4);
                break;
            case R.id.nav_sair:
                goToLogin();
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
        Context context = getApplicationContext();
        SharedPreferences settings = context.getSharedPreferences(USUARIO_CURRICULO, Context.MODE_PRIVATE);
        settings.edit().clear().commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
