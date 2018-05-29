package ranglerz.veterinaryandpatient.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import ranglerz.veterinaryandpatient.CallVetForDairy;
import ranglerz.veterinaryandpatient.FarmSolutionForBirds;
import ranglerz.veterinaryandpatient.FarmSolutionForDairy;
import ranglerz.veterinaryandpatient.OrganizationOfferJobsActivity;
import ranglerz.veterinaryandpatient.R;
import ranglerz.veterinaryandpatient.SaleAnimals;

public class Birds extends Fragment {

    Spinner sp_select_bird;
    RelativeLayout bt_call_a_veteriany, bt_farm_solutino, bt_sale_birds;
    RelativeLayout rl_spiner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v =inflater.inflate(R.layout.fragment_birds, container, false);

        init(v);
        callingVeterinaryActivity();
        farmSolutionActivity();
        btSaleBirdClickHanlder();

        return v;
    }

    private void init(View view){

        sp_select_bird = (Spinner) view.findViewById(R.id.sp_select_bird);
        bt_call_a_veteriany = (RelativeLayout) view.findViewById(R.id.bt_call_a_veteriany);
        bt_farm_solutino = (RelativeLayout) view.findViewById(R.id.bt_farm_solutino);
        bt_sale_birds = (RelativeLayout) view.findViewById(R.id.bt_sale_birds);

        rl_spiner = (RelativeLayout) view.findViewById(R.id.rl_spiner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.birds_categories, R.layout.spinner_item);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        sp_select_bird.setAdapter(adapter);

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

                Intent farmSolution = new Intent(getActivity(), FarmSolutionForBirds.class);
                farmSolution.putExtra("from", "dairy");
                getActivity().startActivity(farmSolution);
            }
        });
    }

    private void btSaleBirdClickHanlder(){

        bt_sale_birds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Animation animShake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);

                int item_position = sp_select_bird.getSelectedItemPosition();
                if (item_position == 0){
                    ((TextView)sp_select_bird.getSelectedView()).setError("Please Select Category");
                    rl_spiner.setAnimation(animShake);

                }else {
                String selectedItem = sp_select_bird.getSelectedItem().toString();
                Intent i = new Intent(getActivity(), SaleAnimals.class);
                i.putExtra("type", "Bird");
                i.putExtra("item", selectedItem);
                startActivity(i);

            }
            }
        });
    }

}
