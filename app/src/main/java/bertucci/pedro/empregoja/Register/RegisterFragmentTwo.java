package bertucci.pedro.empregoja.Register;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import bertucci.pedro.empregoja.R;
import bertucci.pedro.empregoja.interfaces.RequestInterface;
import bertucci.pedro.empregoja.interfaces.RequestInterfaceAddUser;
import bertucci.pedro.empregoja.mascaras.Mask;
import bertucci.pedro.empregoja.models.Areas;
import bertucci.pedro.empregoja.models.Constants;
import bertucci.pedro.empregoja.models.ServerRequest;
import bertucci.pedro.empregoja.models.ServerResponse;
import bertucci.pedro.empregoja.models.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterFragmentTwo extends Fragment  implements View.OnClickListener {

    private FloatingActionButton btn_Cadastrar2;
    private EditText et_cep, et_cpf, et_rg, et_telefone,et_presetencao, et_nascimento;
    private TextView tv_login;
    private ProgressBar progress;
    Context context;
    private AlertDialog alerta;
    private String nome,sobrenome,sexo,senha,email;
    private int Ano, Mes, Dia;
    Calendar myCalendar = Calendar.getInstance();


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_register_second,container,false);
            getActivity().setTitle("Contrata Já - Dados Pessoais");
            Bundle bundle = new Bundle();
            bundle = getArguments();
            email = bundle.getString("email");
            nome = bundle.getString("nome");
            sobrenome = bundle.getString("sobrenome");
            sexo = bundle.getString("genero");
            senha = bundle.getString("password");
            initViews(view);
            return view;
        }




    private void initViews(View view){


        btn_Cadastrar2 = (FloatingActionButton)view.findViewById(R.id.btnCadastrar2);

        et_cep = (EditText) view.findViewById(R.id.et_cep);
        et_presetencao = (EditText) view.findViewById(R.id.et_presencao);
        et_cpf = (EditText) view.findViewById(R.id.et_cpf);
        et_rg  = (EditText)  view.findViewById(R.id.et_rg);
        et_nascimento = (EditText) view.findViewById(R.id.et_nascimento);
        et_telefone = (EditText) view.findViewById(R.id.et_telefone);

        et_cep.addTextChangedListener(Mask.insert(Mask.CEP, et_cep));
        et_cpf.addTextChangedListener(Mask.insert(Mask.CPF, et_cpf));
        et_telefone.addTextChangedListener(Mask.insert(Mask.CELULAR_MASK, et_telefone));
        et_nascimento.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar mcurrentDate=Calendar.getInstance();
                Ano=mcurrentDate.get(Calendar.YEAR);
                Mes=mcurrentDate.get(Calendar.MONTH);
                Dia=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        et_nascimento.setText(selectedday+"/"+selectedmonth+"/"+selectedyear);

                    }
                },Ano, Mes, Dia);
                mDatePicker.setTitle("Selecione sua data de Nascimento");
                mDatePicker.show();
            }


        });
        btn_Cadastrar2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){


            case R.id.btnCadastrar2:

                alert();
                break;

        }

    }

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        et_nascimento.setText(sdf.format(myCalendar.getTime()));
    }

    private void registerProcess(String nome, String sobrenome, String email ,String senha, String cep, String rg, String cpf, String telefone, String pretencao, String sexo,String nascimento){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterfaceAddUser requestInterface = retrofit.create(RequestInterfaceAddUser.class);

        Usuario usuario = new Usuario();
        usuario.setNome_usuario(nome);
        usuario.setSobrenome_usuario(sobrenome);
        usuario.setSexo(sexo);
        usuario.setEmail_usuario(email);
        usuario.setSenha(senha);
        usuario.setCep(cep);
        usuario.setRg(rg);
        usuario.setCpf(cpf);
        usuario.setPretencao(pretencao);
        usuario.setNascimento(nascimento);
        usuario.setTelefone(telefone);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.CADASTRA_USUARIO);
        request.setUser(usuario);
        final Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();

                Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
            }
        });
    }



    private void alert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Termos e Condições");
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
        builder.setPositiveButton("Concordo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
              //PASSA DADOS AQUI PARA registerProcess


                String cep = et_cep.getText().toString();
                String pretencao = et_presetencao.getText().toString();
                String rg = et_rg.getText().toString();
                String cpf = et_cpf.getText().toString();
                String nascimento = et_nascimento.getText().toString();
                String telefone = et_telefone.getText().toString();

                if(!cep.isEmpty() && !rg.isEmpty() && !cpf.isEmpty() && !telefone.isEmpty()) {
                    if(rg.length() == 10){
                        if(cpf.length() == 14){
                            //progress.setVisibility(View.VISIBLE);
                            registerProcess(nome,sobrenome,email,senha,cep,rg,cpf,telefone,pretencao,sexo,nascimento);
                        }else{
                            Snackbar.make(getView(), "Digite CPF por completo!", Snackbar.LENGTH_LONG).show();
                        }
                    }else{
                        Snackbar.make(getView(), "Digite RG por completo!", Snackbar.LENGTH_LONG).show();
                    }


                } else {

                    Snackbar.make(getView(), "Os campos estão vazios!", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Nao Concordo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
              //NAO DEIXA SE CADASTRAR;
            }
        });
        alerta = builder.create();
        alerta.show();
    }

}
