package technomind.in.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import technomind.in.Model.BestOffer;
import technomind.in.R;

public class BestAdapter extends RecyclerView.Adapter<BestAdapter.MyViewHolder> {

    private Context context;
    private List<BestOffer> best;

    public BestAdapter(Context context, List<BestOffer> best) {
        this.context = context;
        this.best = best;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.bestimage);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.best_offers,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BestOffer app = best.get(position);

        holder.mImage.setImageResource(app.getImage());

    }

    @Override
    public int getItemCount() {
        return best.size();
    }


}