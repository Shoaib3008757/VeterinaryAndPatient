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
import ranglerz.veterinaryandpatient.FarmSolutionForDairy;
import ranglerz.veterinaryandpatient.PetSolution;
import ranglerz.veterinaryandpatient.R;


public class FragmentPets extends Fragment {

    Spinner sp_select_pets_category;
    RelativeLayout bt_call_a_veteriany, bt_accessories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v =inflater.inflate(R.layout.pets_fragment, container, false);

        init(v);

        callingVeterinaryActivity();
        farmSolutionActivity();

        return v;
    }

    private void init(View view){

        sp_select_pets_category = (Spinner) view.findViewById(R.id.sp_select_dairy_category);
        bt_call_a_veteriany = (RelativeLayout) view.findViewById(R.id.bt_call_a_veteriany);
        bt_accessories = (RelativeLayout) view.findViewById(R.id.bt_accessories);



        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.pets_categories, R.layout.spinner_item);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        sp_select_pets_category.setAdapter(adapter);

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

        bt_accessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent farmSolution = new Intent(getActivity(), PetSolution.class);
                farmSolution.putExtra("from", "pets");
                getActivity().startActivity(farmSolution);
            }
        });
    }


}
