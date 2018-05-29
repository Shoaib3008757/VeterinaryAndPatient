package ranglerz.veterinaryandpatient;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrganizationOfferJobsActivity extends AppCompatActivity {

    RelativeLayout rl_spiner_salary_range;
    Spinner sp_select_qualification;
    EditText et_job_title, et_salary_range, et_required_experience, et_qualifications, et_description;

    Button bt_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_sale_products);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getSupportActionBar().setTitle(R.string.title_sale_products);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(OrganizationOfferJobsActivity.this ,R.color.colorBlue)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        onSubmitButtonClickHanlder();
    }

    private void init(){

        rl_spiner_salary_range = (RelativeLayout) findViewById(R.id.rl_spiner_salary_range);
        sp_select_qualification = (Spinner) findViewById(R.id.sp_select_qualification);

        et_job_title = (EditText) findViewById(R.id.et_job_title);
        et_salary_range = (EditText) findViewById(R.id.et_salary_range);
        et_required_experience = (EditText) findViewById(R.id.et_required_experience);
        et_qualifications = (EditText) findViewById(R.id.et_qualifications);
        et_description = (EditText) findViewById(R.id.et_description);

        bt_submit = (Button) findViewById(R.id.bt_submit);

        ArrayAdapter adapterProductType = ArrayAdapter.createFromResource(OrganizationOfferJobsActivity.this,
                R.array.product_categories, R.layout.spinner_item);
        adapterProductType.setDropDownViewResource(R.layout.spinner_dropdown_item);
        sp_select_qualification.setAdapter(adapterProductType);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    private void onSubmitButtonClickHanlder(){

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Animation animShake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);

                int spItmePostion = sp_select_qualification.getSelectedItemPosition();
                String etJobTitle = et_job_title.getText().toString();
                String etSalaryRange = et_salary_range.getText().toString();
                String etRequiredExperience = et_required_experience.getText().toString();
                String etQualification = et_qualifications.getText().toString();
                String etDescription = et_description.getText().toString();

                if (spItmePostion == 0){
                    ((TextView)sp_select_qualification.getSelectedView()).setError("Please Select Category");
                    rl_spiner_salary_range.setAnimation(animShake);
                    Toast.makeText(OrganizationOfferJobsActivity.this, "Please Select Category", Toast.LENGTH_SHORT).show();
                } else if (etJobTitle.length() == 0){
                    et_job_title.setAnimation(animShake);
                    et_job_title.setError("Should not empty");
                } else if (etSalaryRange.length() == 0){
                    et_salary_range.setAnimation(animShake);
                    et_salary_range.setError("Should not empty");
                } else if (etRequiredExperience.length() == 0){
                    et_required_experience.setAnimation(animShake);
                    et_required_experience.setError("Should not empty");
                }else if (etQualification.length() == 0){
                    et_qualifications.setAnimation(animShake);
                    et_qualifications.setError("Should not empty");
                }else if (etDescription.length() == 0){
                    et_description.setAnimation(animShake);
                    et_description.setError("Should not empty");
                }else {

                    Log.e("TAG", "values to send selected Category: " + sp_select_qualification.getSelectedItem().toString());
                    Log.e("TAg", "values to send job title: " + etJobTitle);
                    Log.e("TAg", "values to send job title: " + etSalaryRange);
                    Log.e("TAg", "values to send job title: " + etSalaryRange);
                    Log.e("TAg", "values to send job title: " + etRequiredExperience);
                    Log.e("TAg", "values to send job title: " + etQualification);
                    Log.e("TAg", "values to send job title: " + etDescription);

                }
            }
        });
    }
}
