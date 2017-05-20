package bertucci.pedro.empregoja.Register;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import bertucci.pedro.empregoja.Login.LoginFragment;
import bertucci.pedro.empregoja.R;
import bertucci.pedro.empregoja.interfaces.RequestInterface;
import bertucci.pedro.empregoja.models.Constants;
import bertucci.pedro.empregoja.models.ServerRequest;
import bertucci.pedro.empregoja.models.ServerResponse;
import bertucci.pedro.empregoja.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterFragmentTwo extends Fragment  implements View.OnClickListener {

    private FloatingActionButton btnCadastrar;
    private EditText et_cidade,et_estado;
    private TextView tv_login;
    private ProgressBar progress;
    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_second,container,false);
        initViews(view);
        return view;
    }




    private void initViews(View view){
        btnCadastrar = (FloatingActionButton)view.findViewById(R.id.btnCadastrar);
        et_cidade = (EditText)view.findViewById(R.id.et_cidade);


        progress = (ProgressBar)view.findViewById(R.id.progress);

        Geocoder geoCoder = new Geocoder(getActivity(), Locale.getDefault());

        try {
            List<Address> addresses = geoCoder.getFromLocation(-29.7649764, -50.0289451, 1);


            if (addresses.size() > 0)
            {
                et_cidade.setText(addresses.get(0).getLocality());
            }


        }
        catch (IOException e1) {
            e1.printStackTrace();
        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){


            case R.id.btnCadastrar:
                /*
                String name = et_name.getText().toString();
                String sobrenome = et_sobrenome.getText().toString();
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                 if(!name.isEmpty() && !sobrenome.isEmpty() && !email.isEmpty() && !password.isEmpty()) {

                    progress.setVisibility(View.VISIBLE);
                    registerProcess(name,sobrenome,email,password);

                } else {

                    Snackbar.make(getView(), "Os campos estão vazios!", Snackbar.LENGTH_LONG).show();
                }*/
                break;

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
