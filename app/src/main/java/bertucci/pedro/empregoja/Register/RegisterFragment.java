package bertucci.pedro.empregoja.Register;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import bertucci.pedro.empregoja.Login.LoginFragment;
import bertucci.pedro.empregoja.R;
import bertucci.pedro.empregoja.interfaces.RequestInterface;
import bertucci.pedro.empregoja.models.Constants;
import bertucci.pedro.empregoja.models.ServerResponse;
import bertucci.pedro.empregoja.models.ServerRequest;
import bertucci.pedro.empregoja.models.User;

import bertucci.pedro.empregoja.models.Usuarios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterFragment extends Fragment  implements View.OnClickListener{

    private FloatingActionButton btn_register;
    private EditText et_email,et_password,et_password2,et_name, et_sobrenome;
    private TextView tv_login;
    private ProgressBar progress;
    Spinner spinner;
    private String genero;

    String[] Sexo = {
            "Selecione o Genero",
            "Feminino",
            "Masculino"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register,container,false);
        getActivity().setTitle("Contrata Já - Cadastro Usuario");

        spinner = (Spinner)view.findViewById(R.id.et_sexo);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.textview, Sexo);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = spinner.getSelectedItemPosition();
                        genero = Sexo[position];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }

                }
        );
        initViews(view);
        return view;
    }


    private void initViews(View view){
        btn_register = (FloatingActionButton)view.findViewById(R.id.btnCadastrar1);
        et_name = (EditText)view.findViewById(R.id.et_name);
        et_email = (EditText)view.findViewById(R.id.et_email);
        et_password = (EditText)view.findViewById(R.id.et_password);
        et_password2 = (EditText)view.findViewById(R.id.et_password2);
        et_sobrenome = (EditText)view.findViewById(R.id.et_sobrenome);
        progress = (ProgressBar)view.findViewById(R.id.progress);
        btn_register.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCadastrar1:

                String name = et_name.getText().toString();
                String sobrenome = et_sobrenome.getText().toString();
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();
                String password2 = et_password2.getText().toString();

                if(!name.isEmpty() && !sobrenome.isEmpty() && !email.isEmpty() && !password.isEmpty() && !password2.isEmpty()) {
                    if (genero.equals("Selecione o Genero")) {
                        Snackbar.make(getView(), "Genero nao selecionado", Snackbar.LENGTH_LONG).show();
                    } else {

                        if (password.length() >= 6) {
                            if (password.equals(password2)) {
                                progress.setVisibility(View.VISIBLE);
                                Fragment register = new RegisterFragmentTwo();
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.addToBackStack(null);
                                ft.replace(R.id.fragment_frame, register);
                                ft.commit();
                            } else {
                                Snackbar.make(getView(), "Senhas nao sao iguais!", Snackbar.LENGTH_LONG).show();
                            }
                        }else{
                                Snackbar.make(getView(), "Senha a partir de 6 caracteres!", Snackbar.LENGTH_LONG).show();
                            }
                         /* registerProcess(name,sobrenome,email,password);

                         Bundle bundle = new Bundle();
                         bundle.putString("name", name);
                         bundle.putString("sobrenome", sobrenome);
                         bundle.putString("email",email);
                         bundle.putString("password",password);

                         register.setArguments(bundle);*/


                    }
                }else{
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

        Usuarios user = new Usuarios();
      /*  user.setName(name);
        user.setSobrenome(sobrenome);
        user.setEmail(email);
        user.setPassword(password);*/

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
