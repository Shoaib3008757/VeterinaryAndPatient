package ranglerz.veterinaryandpatient;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import org.w3c.dom.Text;

import ranglerz.veterinaryandpatient.Preferences.Prefs;

public class DashboardVeterinarian extends DrawerActivityForVeterinarian {

    TextView tv_name;
    RelativeLayout rl_checkup_requests, rl_find_jobs;
    RelativeLayout rl_offer_jobs, rl_sale_products;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_dashboard_veterinarian);


        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.custome_layout_for_vet_dashboard, null, false);
        mDrawerLayout.addView(contentView, 0);
        toolbar.setTitle(R.string.title_veterinary);




        init();
        onClickLitenerForCheckupRequests();
        onClickLitenerForFindJobs();
        onClickLitenerForOfferJobs();
        onClickLitenerForSaleProducts();

    }//end of onCreate

    private void init(){

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_name.setText(Prefs.getUserFullNameFromPref(getApplicationContext()).toString());
        rl_checkup_requests = (RelativeLayout) findViewById(R.id.rl_checkup_requests);
        rl_find_jobs = (RelativeLayout) findViewById(R.id.rl_find_jobs);
        rl_offer_jobs = (RelativeLayout) findViewById(R.id.rl_offer_jobs);
        rl_sale_products = (RelativeLayout) findViewById(R.id.rl_sale_products);
    }

    private void onClickLitenerForCheckupRequests(){

        rl_checkup_requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(DashboardVeterinarian.this, CheckupRequestsListing.class);
                startActivity(i);


            }
        });
    }
    private void onClickLitenerForFindJobs(){

        rl_find_jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(DashboardVeterinarian.this, SearchJobCategory.class);
                startActivity(i);

            }
        });
    }

    private void onClickLitenerForOfferJobs(){

        rl_offer_jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(DashboardVeterinarian.this, OrganizationOfferJobsActivity.class);
                startActivity(i);

            }
        });
    }
    private void onClickLitenerForSaleProducts(){

        rl_sale_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(DashboardVeterinarian.this, OrganizationSaleProductActivity.class);
                startActivity(i);
            }
        });
    }

}
