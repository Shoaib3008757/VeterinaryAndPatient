package ranglerz.veterinaryandpatient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.nio.channels.OverlappingFileLockException;

public class SignUpAsOrganization extends AppCompatActivity {

    Button bt_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_as_organization);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        init();
        btRegisterButtonClickHandler();
    }

    private void init(){

        bt_register = (Button) findViewById(R.id.bt_register);
    }

    private void btRegisterButtonClickHandler(){

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent verficationActivity = new Intent(SignUpAsOrganization.this, VerificationActivityForOrganization.class);
                startActivity(verficationActivity);
            }
        });
    }
}
