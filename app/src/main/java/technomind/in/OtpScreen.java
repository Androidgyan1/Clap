package technomind.in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OtpScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_screen);
    }

    public void verifaction(View view) {


        Intent  i = new Intent(OtpScreen.this,HomeScreen.class);
        startActivity(i);


    }
}