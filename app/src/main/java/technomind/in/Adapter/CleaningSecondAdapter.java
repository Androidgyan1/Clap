package technomind.in.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import technomind.in.Model.App;
import technomind.in.Model.CleaningSecond;
import technomind.in.R;

public class CleaningSecondAdapter extends RecyclerView.Adapter<CleaningSecondAdapter.MyViewHolder> {

    LayoutInflater inflater;
    List<CleaningSecond> songs;


    public CleaningSecondAdapter(Context ctx, List<CleaningSecond> songs) {
        this.inflater = LayoutInflater.from(ctx);
        this.songs = songs;
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
        View view = inflater.inflate(R.layout.cleaning_second,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CleaningSecond app = songs.get(position);

        holder.mName.setText(app.getName());
        Picasso.get().load(songs.get(position).getImage()).into(holder.mImage);


    }

    @Override
    public int getItemCount() {
        return songs.size();
    }


}