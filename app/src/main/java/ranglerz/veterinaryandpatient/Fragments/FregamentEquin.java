package ranglerz.veterinaryandpatient.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import ranglerz.veterinaryandpatient.CallVetForDairy;
import ranglerz.veterinaryandpatient.EquineSolution;
import ranglerz.veterinaryandpatient.FarmSolutionForDairy;
import ranglerz.veterinaryandpatient.R;

public class FregamentEquin extends Fragment {

    Spinner sp_select_equin;
    RelativeLayout bt_call_a_veteriany, bt_farm_solutino;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v =inflater.inflate(R.layout.equine_fragment, container, false);

        init(v);
        callingVeterinaryActivity();
        farmSolutionActivity();
        return v;
    }

    private void init(View view){

        sp_select_equin = (Spinner) view.findViewById(R.id.sp_select_equin);
        bt_call_a_veteriany = (RelativeLayout) view.findViewById(R.id.bt_call_a_veteriany);
        bt_farm_solutino = (RelativeLayout) view.findViewById(R.id.bt_farm_solutino);


        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.quine_categories, R.layout.spinner_item);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        sp_select_equin.setAdapter(adapter);

    }

    private void callingVeterinaryActivity(){

        bt_call_a_veteriany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent callVet = new Intent(getActivity(), CallVetForDairy.class);
                getActivity().startActivity(callVet);
            }
        });
    }

    private void farmSolutionActivity(){

        bt_farm_solutino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent farmSolution = new Intent(getActivity(), EquineSolution.class);
                farmSolution.putExtra("from", "equine");
                getActivity().startActivity(farmSolution);
            }
        });
    }


}