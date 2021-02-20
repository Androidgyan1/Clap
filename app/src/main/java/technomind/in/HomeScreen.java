package technomind.in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import technomind.in.Adapter.BestAdapter;
import technomind.in.Adapter.CustomAdaptor;
import technomind.in.Model.App;
import technomind.in.Model.BestOffer;


public class HomeScreen extends AppCompatActivity {

    Fragment fragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);



        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new HomeFragment()).commit();

                        break;

                    case R.id.action_porfile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new ProfileFragment()).commit();
                        break;

                    case R.id.action_reward:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new RewardFragment()).commit();
                        break;

                }


                return true;
            }
        });


    }


}