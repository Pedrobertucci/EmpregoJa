package bertucci.pedro.empregoja.Dados;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bertucci.pedro.empregoja.R;

/**
 * Created by pedro on 19/05/17.
 */

public class pessoaisDados extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View pessoais = inflater.inflate(R.layout.pessoais, container, false);
        return pessoais;

    }
}
