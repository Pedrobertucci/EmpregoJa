package bertucci.pedro.empregoja.Register;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import bertucci.pedro.empregoja.Login.LoginFragment;
import bertucci.pedro.empregoja.R;
import bertucci.pedro.empregoja.interfaces.RequestInterface;
import bertucci.pedro.empregoja.models.Constants;
import bertucci.pedro.empregoja.models.ServerResponse;
import bertucci.pedro.empregoja.models.ServerRequest;
import bertucci.pedro.empregoja.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterFragment extends Fragment  implements View.OnClickListener{

    private AppCompatButton btn_register;
    private EditText et_email,et_password,et_name, et_sobrenome;
    private TextView tv_login;
    private ProgressBar progress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){

        btn_register = (AppCompatButton)view.findViewById(R.id.btn_register);
        tv_login = (TextView)view.findViewById(R.id.tv_login);
        et_name = (EditText)view.findViewById(R.id.et_name);
        et_email = (EditText)view.findViewById(R.id.et_email);
        et_password = (EditText)view.findViewById(R.id.et_password);
        et_sobrenome = (EditText)view.findViewById(R.id.et_sobrenome);
        progress = (ProgressBar)view.findViewById(R.id.progress);
        btn_register.setOnClickListener(this);
        tv_login.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_login:
                goToLogin();
                break;

            case R.id.btn_register:

                String name = et_name.getText().toString();
                String sobrenome = et_sobrenome.getText().toString();
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                 if(!name.isEmpty() && !sobrenome.isEmpty() && !email.isEmpty() && !password.isEmpty()) {

                     if(password.length()>= 6){
                         progress.setVisibility(View.VISIBLE);
                         /* registerProcess(name,sobrenome,email,password);

                         Bundle bundle = new Bundle();
                         bundle.putString("name", name);
                         bundle.putString("sobrenome", sobrenome);
                         bundle.putString("email",email);
                         bundle.putString("password",password);

                         register.setArguments(bundle);*/

                         Fragment register = new RegisterFragmentTwo();
                         FragmentTransaction ft = getFragmentManager().beginTransaction();
                         ft.replace(R.id.fragment_frame,register);
                         ft.commit();

                     }else{
                         Snackbar.make(getView(), "Senha a partir de 6 caracteres!", Snackbar.LENGTH_LONG).show();
                     }


                } else {

                    Snackbar.make(getView(), "Os campos estão vazios!", Snackbar.LENGTH_LONG).show();
                }


        }

    }

    private void registerProcess(String name, String sobrenome, String email ,String password){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setName(name);
        user.setSobrenome(sobrenome);
        user.setEmail(email);
        user.setPassword(password);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.REGISTER_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();
                progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG,"Errou");
                Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();


            }
        });
    }

    private void goToLogin(){

        Fragment login = new LoginFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,login);
        ft.commit();
    }



}
