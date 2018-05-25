package ranglerz.veterinaryandpatient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

public class SearchJobCategory extends DrawerActivityForVeterinarian {

    Button bt_search;
    RelativeLayout rl_spiner_job_category, rl_spiner_salary_range;
    Spinner sp_select_job_category, sp_select_salary_range;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //setContentView(R.layout.activity_search_job_category);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_search_job_category, null, false);
        mDrawerLayout.addView(contentView, 0);

        init();
    }

    private void init(){

        bt_search = (Button) findViewById(R.id.bt_search);
        rl_spiner_job_category = (RelativeLayout) findViewById(R.id.rl_spiner_job_category);
        rl_spiner_salary_range = (RelativeLayout) findViewById(R.id.rl_spiner_salary_range);
        sp_select_job_category = (Spinner) findViewById(R.id.sp_select_job_category);
        sp_select_salary_range = (Spinner) findViewById(R.id.sp_select_salary_range);

        ArrayAdapter adapterJobCategory = ArrayAdapter.createFromResource(SearchJobCategory.this,
                R.array.job_categories, R.layout.spinner_item);
        adapterJobCategory.setDropDownViewResource(R.layout.spinner_dropdown_item);
        sp_select_job_category.setAdapter(adapterJobCategory);

        ArrayAdapter adapterSalaryRange = ArrayAdapter.createFromResource(SearchJobCategory.this,
                R.array.salary_range, R.layout.spinner_item);
        adapterSalaryRange.setDropDownViewResource(R.layout.spinner_dropdown_item);
        sp_select_salary_range.setAdapter(adapterSalaryRange);

    }

    private void btSearchClickListener(){

        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }
}
