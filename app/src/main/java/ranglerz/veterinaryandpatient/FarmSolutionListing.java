package ranglerz.veterinaryandpatient;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import ranglerz.veterinaryandpatient.Adapters.ListingDataApaterForFarmSolutions;
import ranglerz.veterinaryandpatient.Models.FarmSolutionData;

public class FarmSolutionListing extends AppCompatActivity {

    RecyclerView rv_farm_solution;
    LinearLayoutManager linearLayoutManager;
    ListingDataApaterForFarmSolutions dataAdapter;
    public ArrayList<FarmSolutionData> farmData;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_solution_listing);

        ini();
    }

    private void ini(){
        rv_farm_solution = (RecyclerView) findViewById(R.id.rv_farm_solution);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext() , LinearLayoutManager.VERTICAL, false);
        rv_farm_solution.setLayoutManager(linearLayoutManager);
        /*DividerItemDecoration itemDecorator = new DividerItemDecoration(FarmSolutionListing.this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(FarmSolutionListing.this, R.drawable.list_item_divider));
        rv_farm_solution.addItemDecoration(itemDecorator);*/
        farmData = new ArrayList<FarmSolutionData>();

        for (int i = 0; i<=5; i++) {
            farmData.add(new FarmSolutionData("This is Title "+ i, "Sample " + i, "Sample "+ i, "Sample "+ i));
        }


        dataAdapter = new ListingDataApaterForFarmSolutions(FarmSolutionListing.this,  farmData);
        rv_farm_solution.setAdapter(dataAdapter);

    }


}





