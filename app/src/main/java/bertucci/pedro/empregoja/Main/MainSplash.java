package bertucci.pedro.empregoja.Main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bertucci.pedro.empregoja.R;
import bertucci.pedro.empregoja.interfaces.RequestInterface;
import bertucci.pedro.empregoja.models.Constants;
import bertucci.pedro.empregoja.models.ServerRequest;
import bertucci.pedro.empregoja.models.ServerResponse;
import bertucci.pedro.empregoja.models.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static bertucci.pedro.empregoja.Login.LoginFragment.USUARIO_CURRICULO;

public class MainSplash extends AppCompatActivity {

    private String usuario,senha;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = getApplicationContext().getSharedPreferences(USUARIO_CURRICULO, MODE_PRIVATE);
        SharedPreferences shared = getSharedPreferences(USUARIO_CURRICULO, MODE_PRIVATE);
        usuario = (shared.getString("usuario", ""));
        senha = (shared.getString("senha",""));

        setContentView(R.layout.activity_main_splash);

        Handler handle = new Handler();

        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(usuario.length()>0 && senha.length() > 0){
                    loginProcess(usuario,senha);

                }else{
                    Intent intent = new Intent(MainSplash.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        }, 3000);
    }

    private void loginProcess(String email, final String password){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        Usuario usuario = new Usuario();
        usuario.setEmail_usuario(email);
        usuario.setSenha_usuario(password);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.LOGIN_USUARIO);
        request.setUser(usuario);
        final Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();


                if(resp.getResult().equals(Constants.SUCCESS)){

                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("usuario", resp.getUsuario().getEmail_usuario());
                    editor.putString("senha",password);
                    editor.putString("id_usuario",resp.getUsuario().getId_usuario());
                    editor.commit();


                    Intent in = new Intent(MainSplash.this, MainProfile.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("nome",resp.getUsuario().getNome_usuario());
                    bundle.putString("email",resp.getUsuario().getEmail_usuario());
                    bundle.putString("id_usuario",resp.getUsuario().getId_usuario());
                    in.putExtras(bundle);
                    startActivity(in);
                    finish();


                }

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {


            }
        });
    }
}
