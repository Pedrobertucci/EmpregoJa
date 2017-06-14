package bertucci.pedro.empregoja.Login;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import bertucci.pedro.empregoja.Main.MainProfile;
import bertucci.pedro.empregoja.R;
import bertucci.pedro.empregoja.Register.RegisterFragment;
import bertucci.pedro.empregoja.interfaces.RequestInterface;
import bertucci.pedro.empregoja.models.Constants;
import bertucci.pedro.empregoja.models.ServerResponse;
import bertucci.pedro.empregoja.models.ServerRequest;

import bertucci.pedro.empregoja.models.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginFragment extends Fragment implements View.OnClickListener{

    private AppCompatButton btn_login;
    private EditText et_email,et_password;
    private TextView tv_register;
    private ProgressBar progress;
    private SharedPreferences pref;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        getActivity().setTitle(R.string.app_name);
        initViews(view);
        return view;
    }




    private void initViews(View view){

        pref = getActivity().getPreferences(0);

        btn_login = (AppCompatButton)view.findViewById(R.id.btn_login);
        tv_register = (TextView)view.findViewById(R.id.tv_register);
        et_email = (EditText)view.findViewById(R.id.et_email);
        et_password = (EditText)view.findViewById(R.id.et_password);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        btn_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tv_register:
                goToRegister();
                break;

            case R.id.btn_login:
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                if(!email.isEmpty() && !password.isEmpty()) {

                    progress.setVisibility(View.VISIBLE);
                    loginProcess(email,password);

                } else {

                    Snackbar.make(getView(), "Preencha os Campos!", Snackbar.LENGTH_LONG).show();
                }
                break;

        }
    }
    private void loginProcess(String email,String password){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(password);
        ServerRequest request = new ServerRequest();
            request.setOperation(Constants.LOGIN_USUARIO);
        request.setUser(usuario);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();

                if(resp.getResult().equals(Constants.SUCCESS)){

                    /*Toast toast = Toast.makeText(getActivity(),"Nome:"+resp.getUsuario().getNome_usuario()+"" +
                            " Email: "+resp.getUsuario().getEmail()+" ", Toast.LENGTH_LONG);
                    toast.show();*/

                    Intent in = new Intent(getActivity(), MainProfile.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("nome",resp.getUsuario().getNome());
                    bundle.putString("email",resp.getUsuario().getEmail());
                    bundle.putString("id_usuario",resp.getUsuario().getId_usuario());
                    in.putExtras(bundle);
                    startActivity(in);


                }
                progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                progress.setVisibility(View.INVISIBLE);
                Snackbar.make(getView(),  t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();

            }
        });
    }

    private void goToRegister(){

        Fragment register = new RegisterFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.fragment_frame,register);
        ft.commit();
    }


}
