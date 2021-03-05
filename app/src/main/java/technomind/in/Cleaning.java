package technomind.in;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import technomind.in.Adapter.CleaningAdapter;
import technomind.in.Adapter.CleaningSecondAdapter;
import technomind.in.Config.Config;
import technomind.in.Model.CleaningSecond;


public class Cleaning extends AppCompatActivity {

    RecyclerView cleaning,recyclerView;
    List<technomind.in.Model.Cleaning> cleaningList;


    List<CleaningSecond> songs;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning);

        cleaning =findViewById(R.id.recycle_cleaning);
        recyclerView =findViewById(R.id.recycle_cleaningsecond);

        cleaningList = new ArrayList<>();
//second
        songs = new ArrayList<>();

        extractCleaning();



        cleaningList.add(new technomind.in.Model.Cleaning(R.drawable.best_one));
        cleaningList.add(new technomind.in.Model.Cleaning(R.drawable.best_two));
        final LinearLayoutManager managerbest = new LinearLayoutManager(this);
        managerbest.setOrientation(LinearLayoutManager.HORIZONTAL);
        cleaning.setLayoutManager(managerbest);

        CleaningAdapter adaptorbest = new CleaningAdapter(this,cleaningList);

        cleaning.setAdapter(adaptorbest);


        ////second Adapte
    }

    private void extractCleaning() {

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Config.JSON_URLCleaning, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject songObject = response.getJSONObject(i);

                        CleaningSecond song = new CleaningSecond();
                        song.setName(songObject.getString("song").toString());
                        song.setImage(songObject.getInt("cover_image"));
                        songs.add(song);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                CleaningSecondAdapter cleaningSecondAdapter = new CleaningSecondAdapter(getApplicationContext(),songs);
                recyclerView.setAdapter( cleaningSecondAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);


    }
}