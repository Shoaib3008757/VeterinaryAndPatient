package ranglerz.veterinaryandpatient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class ClientTabbtherSolution extends DrawerActivityForClient {

    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_pet_solution);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.custome_screen_for_other_tab, null, false);
        mDrawerLayout.addView(contentView, 0);


        init();
        //
        // farmSolutionButtonClickHandler();
    }


    private void init(){
        tv_title = (TextView) findViewById(R.id.tv_title);
        // bt_accessories = (Button) findViewById(R.id.bt_accessories);


        Intent resultIntent = getIntent();
        String result = resultIntent.getExtras().getString("from");
        if (result.equals("dairy")){


        }
        else if (result.equals("other")){


        }
        else if (result.equals("pets")){


        }
        else if (result.equals("equine")){


        }


    }

  /*  private void farmSolutionButtonClickHandler(){
        bt_accessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(FarmSolutionForDairy.this, FarmSolutionListing.class);
                startActivity(i);

            }
        });
    }*/
}
