package technomind.in.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import technomind.in.Model.App;
import technomind.in.Model.CleaningSecond;
import technomind.in.R;

public class CleaningSecondAdapter extends RecyclerView.Adapter<CleaningSecondAdapter.MyViewHolder> {

    private Context context;
    private List<CleaningSecond> apps;

    public CleaningSecondAdapter(Context context, List<CleaningSecond> apps) {
        this.context = context;
        this.apps = apps;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mName;
        ImageView mImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.cleaning_text);
            mImage = itemView.findViewById(R.id.cleaningimage);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cleaning_second,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CleaningSecond app = apps.get(position);

        holder.mName.setText(app.getName());
        holder.mImage.setImageResource(app.getImage());


    }

    @Override
    public int getItemCount() {
        return apps.size();
    }


}