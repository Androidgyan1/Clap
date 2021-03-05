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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import technomind.in.Adapter.BestAdapter;
import technomind.in.Adapter.CleaningSecondAdapter;
import technomind.in.Adapter.CustomAdaptor;
import technomind.in.Config.Config;
import technomind.in.Model.App;
import technomind.in.Model.BestOffer;
import technomind.in.Model.CleaningSecond;
import technomind.in.Model.List_Data;


public class HomeFragment extends Fragment {

    ///slider

    SliderLayout sliderLayout;
    private List<List_Data> list_dataList;

    private JsonArrayRequest request;
    private RequestQueue requestQueue;





    RecyclerView mList1,bestofffers;
    List<App> appList;
    List<BestOffer> bestOffersList;


    TextView addres;
    FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        ///slider

        sliderLayout = (SliderLayout) view.findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.SLIDE);

        list_dataList=new ArrayList<>();
        sliderLayout.setScrollTimeInSec(4);


        SliderView sliderView = new SliderView(getActivity());
        setSliderViews();




        ///recycle category
        mList1 = view.findViewById(R.id.recyclew);
        bestofffers = view.findViewById(R.id.recyclebest);
        appList = new ArrayList<>();
        bestOffersList = new ArrayList<>();

        ///bestoffers
        extractCleaning();

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







































        addres = view.findViewById(R.id.location);
        addres.setSelected(true);

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

    private void extractCleaning() {

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Config.BestOfffers, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject songObject = response.getJSONObject(i);

                        BestOffer song = new BestOffer();
                        song.setImage(songObject.getInt("cover_image"));
                        bestOffersList.add(song);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                final LinearLayoutManager managerbest = new LinearLayoutManager(getActivity());
                managerbest.setOrientation(LinearLayoutManager.HORIZONTAL);
                bestofffers.setLayoutManager(managerbest);

                BestAdapter adaptorbest = new BestAdapter(getActivity(),bestOffersList);

                bestofffers.setAdapter(adaptorbest);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);


    }



    private void setSliderViews() {
        request = new JsonArrayRequest(Config.HI, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        List_Data listData = new List_Data(jsonObject.getString("imageurl"));
                        list_dataList.add(listData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setupdata(list_dataList);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);

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

    private void setupdata(List<List_Data> list_dataList) {

        for (int i = 0; i <= 4; i++) {
            final List_Data ld = list_dataList.get(i);
            SliderView sliderView = new SliderView(getActivity());
            sliderView.setImageUrl(ld.getImageurl());
            sliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);
            final int finalI = i;
            sliderLayout.addSliderView(sliderView);

        }
    }

}