package technomind.in;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import technomind.in.Adapter.CleaningAdapter;
import technomind.in.Adapter.CleaningSecondAdapter;
import technomind.in.Model.CleaningSecond;


public class Cleaning extends AppCompatActivity {

    RecyclerView cleaning,cleaningsecond;
    List<technomind.in.Model.Cleaning> cleaningList;
    List<CleaningSecond> cleaningSecondList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning);

        cleaning =findViewById(R.id.recycle_cleaning);
        cleaningsecond =findViewById(R.id.recycle_cleaningsecond);

        cleaningList = new ArrayList<>();

        cleaningSecondList = new ArrayList<>();


        cleaningList.add(new technomind.in.Model.Cleaning(R.drawable.best_one));
        cleaningList.add(new technomind.in.Model.Cleaning(R.drawable.best_two));
        final LinearLayoutManager managerbest = new LinearLayoutManager(this);
        managerbest.setOrientation(LinearLayoutManager.HORIZONTAL);
        cleaning.setLayoutManager(managerbest);

        CleaningAdapter adaptorbest = new CleaningAdapter(this,cleaningList);

        cleaning.setAdapter(adaptorbest);


        ////second Adapter

        cleaningSecondList.add(new CleaningSecond(R.drawable.bathroom_cleaning,"Professional Bathroom Cleaning"));
        cleaningSecondList.add(new CleaningSecond(R.drawable.residintal_cleaning,"Professional Full Home"));
        cleaningSecondList.add(new CleaningSecond(R.drawable.professional_cleaning,"Professional Sofa Cleaning"));
        cleaningSecondList.add(new CleaningSecond(R.drawable.unnamed,"Professional Kietchan Cleaning"));
        cleaningSecondList.add(new CleaningSecond(R.drawable.pro_clean,"Professional Office Cleaning"));
        final LinearLayoutManager managerbestSecondCleaning = new LinearLayoutManager(this);
        managerbestSecondCleaning.setOrientation(LinearLayoutManager.VERTICAL);
        cleaningsecond.setLayoutManager(managerbestSecondCleaning);

        CleaningSecondAdapter adaptorbestSecondAdapter = new CleaningSecondAdapter(this,cleaningSecondList);

        cleaningsecond.setAdapter(adaptorbestSecondAdapter);

    }
}