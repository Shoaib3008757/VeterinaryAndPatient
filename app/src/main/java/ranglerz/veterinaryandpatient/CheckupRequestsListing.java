package ranglerz.veterinaryandpatient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

import ranglerz.veterinaryandpatient.Adapters.ListingAdapterCheckupRequest;
import ranglerz.veterinaryandpatient.Adapters.ListingDataApaterForFarmSolutions;
import ranglerz.veterinaryandpatient.Models.FarmSolutionData;

public class CheckupRequestsListing extends DrawerActivityForVeterinarian {

    RecyclerView rv_checkup_requests;
    LinearLayoutManager linearLayoutManager;
    ListingAdapterCheckupRequest dataAdapter;
    public ArrayList<FarmSolutionData> farmData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_checkup_requests_listing);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_checkup_requests_listing, null, false);
        mDrawerLayout.addView(contentView, 0);

        ini();

    }


    private void ini(){
        rv_checkup_requests = (RecyclerView) findViewById(R.id.rv_checkup_requests);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext() , LinearLayoutManager.VERTICAL, false);
        rv_checkup_requests.setLayoutManager(linearLayoutManager);
        /*DividerItemDecoration itemDecorator = new DividerItemDecoration(FarmSolutionListing.this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(FarmSolutionListing.this, R.drawable.list_item_divider));
        rv_farm_solution.addItemDecoration(itemDecorator);*/
        farmData = new ArrayList<FarmSolutionData>();

        for (int i = 0; i<=5; i++) {
            farmData.add(new FarmSolutionData("This is Title "+ i, "Name here " + i, "Age here "+ i, "Distance here "+ i));
        }


        dataAdapter = new ListingAdapterCheckupRequest(CheckupRequestsListing.this,  farmData);
        rv_checkup_requests.setAdapter(dataAdapter);

    }


}
