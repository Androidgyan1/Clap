package technomind.in.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import technomind.in.Model.Cleaning;
import technomind.in.R;

public class CleaningAdapter extends RecyclerView.Adapter<CleaningAdapter.MyViewHolder> {

    private Context context;
    private List<Cleaning> cleaning;

    public CleaningAdapter(Context context, List<Cleaning> cleaning) {
        this.context = context;
        this.cleaning = cleaning;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.cleaningimage);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_cleaning,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cleaning clean = cleaning.get(position);

        holder.mImage.setImageResource(clean.getImage());

    }

    @Override
    public int getItemCount() {
        return cleaning.size();
    }


}