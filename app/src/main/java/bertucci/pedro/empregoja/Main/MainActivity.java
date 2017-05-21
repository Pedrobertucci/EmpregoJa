package bertucci.pedro.empregoja.Main;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bertucci.pedro.empregoja.Login.LoginFragment;
import bertucci.pedro.empregoja.R;

public class MainActivity extends AppCompatActivity  {

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getPreferences(0);
        initFragment();
    }

        private void initFragment(){
            Fragment fragment;
            fragment = new LoginFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.fragment_frame,fragment);
            ft.commit();
        }




}
