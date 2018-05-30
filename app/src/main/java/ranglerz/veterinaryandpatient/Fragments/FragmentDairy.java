package ranglerz.veterinaryandpatient.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import ranglerz.veterinaryandpatient.CallVetForDairy;
import ranglerz.veterinaryandpatient.FarmSolutionForDairy;
import ranglerz.veterinaryandpatient.R;
import ranglerz.veterinaryandpatient.SaleAnimals;


public class FragmentDairy extends Fragment {

    Spinner sp_select_dairy_category;
    RelativeLayout bt_call_a_veteriany, bt_farm_solutino, bt_sale_dairy;
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
        final View v =inflater.inflate(R.layout.dairy_fragment, container, false);

        init(v);
        callingVeterinaryActivity();
        farmSolutionActivity();
        btSaleDairyClickHanlder();


        return v;
    }

    private void init(View view){

        sp_select_dairy_category = (Spinner) view.findViewById(R.id.sp_select_dairy_category);
        bt_call_a_veteriany = (RelativeLayout) view.findViewById(R.id.bt_call_a_veteriany);
        bt_farm_solutino = (RelativeLayout) view.findViewById(R.id.bt_farm_solutino);
        bt_sale_dairy = (RelativeLayout) view.findViewById(R.id.bt_sale_dairy);
        rl_spiner = (RelativeLayout) view.findViewById(R.id.rl_spiner);


        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.dairy_categories, R.layout.spinner_item);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        sp_select_dairy_category.setAdapter(adapter);

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

                Intent farmSolution = new Intent(getActivity(), FarmSolutionForDairy.class);
                farmSolution.putExtra("from", "dairy");
                getActivity().startActivity(farmSolution);
            }
        });
    }

    private void btSaleDairyClickHanlder(){

        bt_sale_dairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Animation animShake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);

           /*     int item_position = sp_select_dairy_category.getSelectedItemPosition();
                if (item_position == 0){
                    ((TextView)sp_select_dairy_category.getSelectedView()).setError("Please Select Category");
                    rl_spiner.setAnimation(animShake);

                }else {*/

                   // String selectedItem = sp_select_dairy_category.getSelectedItem().toString();
                    Intent i = new Intent(getActivity(), SaleAnimals.class);
                    i.putExtra("type", "Dairy");
                    //i.putExtra("item", selectedItem);
                    startActivity(i);

                //}
            }
        });
    }


}
