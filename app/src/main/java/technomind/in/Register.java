package technomind.in;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import technomind.in.Config.Config;

public class Register extends AppCompatActivity implements View.OnClickListener {

    //Creating views
    private EditText editTextConfirmOtp;

    private Button buttonRegister;

    //Volley RequestQueue
    private RequestQueue requestQueue;


    //String variables to hold username password and phone
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initializing Views
        editTextConfirmOtp = (EditText) findViewById(R.id.mobile);

        //Initializing the RequestQueue
        requestQueue = Volley.newRequestQueue(this);

        //Adding a listener to button
        buttonRegister.setOnClickListener(this);

    }


    public void register(View view) {

        Intent i = new Intent(Register.this, OtpScreen.class);
        startActivity(i);

    }

    @Override
    public void onClick(View view) {
        //Calling register method on register button click
        register();
    }

    private void register() {
        //Displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Registering", "Please wait...", false, false);

        //Getting user data
        phone = editTextConfirmOtp.getText().toString().trim();

        //Again creating the string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        try {
                            //Creating the json object from the response
                            JSONObject jsonResponse = new JSONObject(response);

                            //If it is success
                            if (jsonResponse.getString(Config.TAG_RESPONSE).equalsIgnoreCase("Success")) {
                                //Asking user to confirm otp
                                Intent i = new Intent(Register.this, OtpScreen.class);
                                startActivity(i);
                            } else {
                                //If not successful user may already have registered
                                Toast.makeText(Register.this, "Username or Phone number already registered", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(Register.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding the parameters to the request
                params.put(Config.KEY_PHONE, phone);
                return params;
            }
        };

        //Adding request the the queue
        requestQueue.add(stringRequest);

    }
}