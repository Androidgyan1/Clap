package technomind.in;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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


public class HomeFragment extends Fragment {

    RecyclerView mList1,bestofffers;
    List<App> appList;
    List<BestOffer> bestOffersList;

    TextView addres;
    FusedLocationProviderClient fusedLocationProviderClient;

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.cleaning, R.drawable.cleaning_two, R.drawable.cleaning_three};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        ///recycle category
        mList1 = view.findViewById(R.id.recyclew);
        bestofffers = view.findViewById(R.id.recyclebest);
        appList = new ArrayList<>();
        bestOffersList = new ArrayList<>();

        bestOffersList.add(new BestOffer(R.drawable.best_one));
        bestOffersList.add(new BestOffer(R.drawable.best_two));
        final LinearLayoutManager managerbest = new LinearLayoutManager(getActivity());
        managerbest.setOrientation(LinearLayoutManager.HORIZONTAL);
        bestofffers.setLayoutManager(managerbest);

        BestAdapter adaptorbest = new BestAdapter(getActivity(),bestOffersList);

        bestofffers.setAdapter(adaptorbest);






        appList.add(new App(R.drawable.ic_appliance_repair,"Appliance"));
        appList.add(new App(R.drawable.ic_carpenter,"Carpanter"));
        appList.add(new App(R.drawable.ic_clning,"Cleaning"));
        appList.add(new App(R.drawable.ic_plumber,"Plumber"));
        appList.add(new App(R.drawable.ic_salon_at_home,"Salon"));
        appList.add(new App(R.drawable.ic_electraican,"Electrician"));

        final LinearLayoutManager manager1 = new LinearLayoutManager(getActivity());
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mList1.setLayoutManager(manager1);

        CustomAdaptor adaptor1 = new CustomAdaptor(getActivity(),appList);

        mList1.setAdapter(adaptor1);







































        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        addres = view.findViewById(R.id.location);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        if (ActivityCompat.checkSelfPermission(getActivity()
                , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location();

        }else {
            ActivityCompat.requestPermissions(getActivity()
                    , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }


        return view;
    }

    private void Location() {

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(getActivity()
                                , Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );

                        addres.setText(Html.fromHtml(
                                "<font color = '#6200EE'><br></font>"
                                        + addresses.get(0).getAddressLine(0)
                        ));


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
}