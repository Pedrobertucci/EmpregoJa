package bertucci.pedro.empregoja.Register;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

    private FloatingActionButton btn_Cadastrar2;
    private EditText et_cidade,et_estado;
    private TextView tv_login;
    private ProgressBar progress;
    Context context;
    private AlertDialog alerta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_second,container,false);
        getActivity().setTitle("Trabalho ja - Dados Pessoais");
        initViews(view);
        return view;
    }




    private void initViews(View view){
        btn_Cadastrar2 = (FloatingActionButton)view.findViewById(R.id.btnCadastrar2);
        et_cidade = (EditText)view.findViewById(R.id.et_cidade);
        btn_Cadastrar2.setOnClickListener(this);
        Geocoder geoCoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<Address> addresses = geoCoder.getFromLocation(-29.7649764, -50.0289451, 1);
            if (addresses.size() > 0){
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


            case R.id.btnCadastrar2:

                alert();
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

    private void alert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Termos e Condiçoes");
        builder.setMessage("Duis iaculis id nisi a vestibulum. Proin et posuere leo, pulvinar varius turpis. Morbi ornare ligula a tortor pulvinar, eu mattis felis consequat. In ac posuere tellus. Nunc sodales metus id nisl laoreet, ut vestibulum risus scelerisque. Maecenas pharetra gravida ligula in rutrum. Nullam commodo ornare accumsan. Phasellus libero nibh, bibendum a eros vel, suscipit pharetra lorem.\n" +
                "\n" +
                "Integer in ipsum nisi. Integer hendrerit aliquam urna nec tincidunt. Praesent eleifend tellus non augue sagittis commodo. Aliquam non bibendum erat, ac tristique orci. Ut mauris nisl, eleifend eu rhoncus at, porta sit amet felis. Aliquam venenatis, nulla ut egestas pellentesque, quam elit viverra ante, at pulvinar ante leo a mi. Mauris sed pharetra tortor. In mollis justo justo, a condimentum odio blandit in. Praesent ut fermentum sem. Nunc leo nulla, tincidunt a molestie vel, vulputate at tortor. Nullam nec fringilla est, quis dignissim ligula. Vivamus feugiat erat vel nunc blandit, id tincidunt sem porttitor. Etiam dolor nunc, scelerisque et mollis eget, congue quis felis. Donec rutrum finibus tortor vel fringilla. Etiam vel lacus a magna elementum vehicula.\n" +
                "\n" +
                "Sed placerat magna eu elementum egestas. Donec ultrices faucibus sapien nec ullamcorper. Nam aliquam est semper augue venenatis pellentesque. Morbi odio nisi, auctor sit amet lobortis et, elementum non turpis. Aliquam erat volutpat. Duis pellentesque, tortor nec egestas tempus, dui metus luctus lorem, eu sollicitudin lacus lacus ut ligula. Vestibulum blandit dui lorem, vitae ultricies libero vestibulum quis.\n" +
                "\n" +
                "Nulla dignissim tellus sed massa blandit finibus. Vestibulum maximus convallis justo, interdum suscipit odio tempus eu. Duis a quam et justo accumsan convallis. Fusce malesuada turpis vel purus tempor, in gravida orci pulvinar. Aenean fringilla non turpis condimentum luctus. Sed facilisis massa in nibh aliquet bibendum. Nulla malesuada nisl eget tempus fringilla. Curabitur vel lacus ipsum. Sed eu interdum ante, a ultrices lacus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis vitae suscipit nulla, id elementum elit. Nam non dignissim justo. Duis tristique risus sapien, non molestie justo efficitur vitae. Mauris ac sodales risus.\n" +
                "\n" +
                "Nunc eros mauris, vehicula non fermentum ut, egestas ut urna. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aliquam risus ex, sodales et metus et, interdum vulputate lacus. Donec non nisl vel massa consequat ultricies vel et lectus. Phasellus iaculis eget nisl nec tempor. Mauris ullamcorper massa nec nisl finibus, a egestas neque fermentum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Morbi ac molestie neque. Nulla purus ex, feugiat eget ipsum sit amet, aliquet lacinia est. Sed dictum aliquet dui, vitae sollicitudin neque semper vel. In libero leo, sollicitudin a leo vel, suscipit sagittis felis. Cras nisl enim, tincidunt ac varius eu, gravida et augue. Nullam diam dolor, mollis ut nunc nec, faucibus tempus ex.\n" +
                "\n" +
                "Nulla facilisi. Morbi mi sapien, aliquam non nibh quis, fermentum tempor risus. Maecenas fringilla pellentesque felis. Fusce commodo felis nibh, sed blandit felis accumsan vitae. Praesent tellus lorem, faucibus ut dolor a, rutrum suscipit ipsum. Etiam finibus non metus mollis gravida. Morbi in arcu at magna bibendum ullamcorper. Donec finibus justo nec felis consectetur ultricies. Donec pretium pretium enim id mattis. Curabitur aliquet purus eget enim finibus, at cursus nisi ullamcorper.");
        builder.setPositiveButton("Positivo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
              //PASSA DADOS AQUI PARA registerProcess
            }
        });
        builder.setNegativeButton("Negativo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
              //NAO DEIXA SE CADASTRAR;
            }
        });
        alerta = builder.create();
        alerta.show();
    }
}
