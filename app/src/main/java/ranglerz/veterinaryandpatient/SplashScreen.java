package ranglerz.veterinaryandpatient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import ranglerz.veterinaryandpatient.Preferences.Prefs;

public class SplashScreen extends AppCompatActivity {

    private static int SplashScreenTimeOut = 3000;//3 seconds8
    private int timer = 3;
    Handler mHandler;
    //TextView tv_please_wait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mHandler = new Handler();
        useHandler();
        // tv_please_wait = (TextView) findViewById(R.id.tv_please_wait);


    }

    //Thread for starting mainActivity
    private Runnable mRunnableStartMainActivity = new Runnable() {
        @Override
        public void run() {
            Log.d("Handler", " Calls");
            timer--;
            mHandler = new Handler();
            mHandler.postDelayed(this, 1000);

            if (timer == 2) {
                //tv_please_wait.setText("Please Wait...");
            }
            if (timer == 1) {
                //tv_please_wait.setText("Please Wait.");
            }
            if (timer == 0) {

                SharedPreferences sharedPreferences = getSharedPreferences("user", 0);
                if (sharedPreferences!=null){
                    String pin =  sharedPreferences.getString("pincode", "null");
                    if (!pin.equals("null")){

                        Log.e("TAg", "the pin code is: " + pin);
                        Intent i = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(i);
                        finish();

                    }
                    else {


                        String userIDFromPref = Prefs.getUserRoleFromPref(getApplicationContext());
                        Log.e("TAG", "User id From pref is: " + userIDFromPref);
                        if (userIDFromPref.equals("-1")) {
                            Intent i = new Intent(SplashScreen.this, UserLoginActivity.class);
                           /* startActivity(i);
                            finish();*/
                        }
                        else if (userIDFromPref.equals("veterinarian")){

                            Intent i = new Intent(SplashScreen.this, DashboardVeterinarian.class);
                           /* startActivity(i);
                            finish();*/

                        }

                        //Intent i = new Intent(SplashScreen.this, DashBoardOrganization.class);
                         Intent i = new Intent(SplashScreen.this, UserLoginActivity.class);
                            startActivity(i);
                            finish();
                       // Intent i = new Intent(SplashScreen.this, ProfileUpdateForVeterinary.class);
                        //Intent i = new Intent(SplashScreen.this, GettingMobileNumberActivity.class);
                        // Intent i = new Intent(SplashScreen.this, FranchiserRegistration.class);


                    }
                }

            }
        }
    };


    //handler for the starign activity
    Handler newHandler;
    public void useHandler(){

        newHandler = new Handler();
        newHandler.postDelayed(mRunnableStartMainActivity, 1000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mRunnableStartMainActivity);
    }
}//***************** Shoaib Anwar ********************