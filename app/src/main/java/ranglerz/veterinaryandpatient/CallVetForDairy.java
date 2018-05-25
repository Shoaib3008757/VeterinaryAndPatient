package ranglerz.veterinaryandpatient;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class CallVetForDairy extends DrawerActivityForClient {

    RelativeLayout bt_medication, bt_nutritionist, bt_breeding, bt_sergeon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_call_vet_for_dairy);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_call_vet_for_dairy, null, false);
        mDrawerLayout.addView(contentView, 0);

        init();
        startingMapActivity();
        btNuetritionClickHanlder();
        btBreedingClickHanlder();
        btSurGeonClickHandler();

    }

    private void init(){

        bt_medication = (RelativeLayout) findViewById(R.id.bt_medication);
        bt_nutritionist = (RelativeLayout) findViewById(R.id.bt_nutritionist);
        bt_breeding = (RelativeLayout) findViewById(R.id.bt_breeding);
        bt_sergeon = (RelativeLayout) findViewById(R.id.bt_sergeon);
    }

    private void startingMapActivity(){

        bt_medication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(CallVetForDairy.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_treament);
                final RadioGroup dialog_radio_group = (RadioGroup) dialog.findViewById(R.id.dialog_radio_group);
                Button bt_dialog_find = (Button) dialog.findViewById(R.id.bt_dialog_find);
                bt_dialog_find.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();
                        int radioButtonID = dialog_radio_group.getCheckedRadioButtonId();
                        View radioButton = dialog_radio_group.findViewById(radioButtonID);
                        int idx = dialog_radio_group.indexOfChild(radioButton);
                        Log.e("TAG", "the seleted Radio Group id is: " + idx);
                        if (idx == -1){
                            Toast.makeText(CallVetForDairy.this, "Please Select a Category", Toast.LENGTH_SHORT).show();

                        }else {
                            Intent intent = new Intent(CallVetForDairy.this, MapsActivityForCallVet.class);
                            startActivity(intent);
                        }
                    }
                });

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationTooDouen;
                dialog.show();

            }
        });


    }

    private void btNuetritionClickHanlder(){

        bt_nutritionist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(CallVetForDairy.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_number_of_animals);
                Button bt_dialog_find = (Button) dialog.findViewById(R.id.bt_dialog_find);
                bt_dialog_find.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();


                            Intent intent = new Intent(CallVetForDairy.this, MapsActivityForCallVet.class);
                            startActivity(intent);


                    }
                });

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationTooDouen;
                dialog.show();
            }
        });
    }
    private void btBreedingClickHanlder(){

        bt_breeding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(CallVetForDairy.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_for_breeding);

                final RadioGroup dialog_radio_group = (RadioGroup) dialog.findViewById(R.id.dialog_radio_group);
                Button bt_dialog_find = (Button) dialog.findViewById(R.id.bt_dialog_find);
                bt_dialog_find.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();
                        int radioButtonID = dialog_radio_group.getCheckedRadioButtonId();
                        View radioButton = dialog_radio_group.findViewById(radioButtonID);
                        int idx = dialog_radio_group.indexOfChild(radioButton);
                        Log.e("TAG", "the seleted Radio Group id is: " + idx);
                        if (idx == -1){
                            Toast.makeText(CallVetForDairy.this, "Please Select a Category", Toast.LENGTH_SHORT).show();

                        }
                        else {
                        Intent intent = new Intent(CallVetForDairy.this, MapsActivityForCallVet.class);
                        startActivity(intent);
                    }

                    }
                });

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationTooDouen;
                dialog.show();
            }
        });
    }

    private void btSurGeonClickHandler(){
        bt_sergeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(CallVetForDairy.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_number_of_animals);
                Button bt_dialog_find = (Button) dialog.findViewById(R.id.bt_dialog_find);
                bt_dialog_find.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();

                        Intent intent = new Intent(CallVetForDairy.this, MapsActivityForCallVet.class);
                        startActivity(intent);
                    }
                });

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationTooDouen;
                dialog.show();
            }
        });
    }
}
