package bertucci.pedro.empregoja.perfil;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bertucci.pedro.empregoja.R;

/**
 * Created by pedro on 13/05/17.
 */

public class Empregos extends Fragment implements View.OnClickListener {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.empregos,container,false);
        return view;

    }

    @Override
    public void onClick(View v) {

    }
}
