package technomind.in.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import technomind.in.Model.BestOffer;
import technomind.in.R;

public class BestAdapter extends RecyclerView.Adapter<BestAdapter.MyViewHolder> {

    LayoutInflater inflater;
    private List<BestOffer> best;

    public BestAdapter(Context ctx, List<BestOffer> best) {
        this.inflater = LayoutInflater.from(ctx);
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
        View view = inflater.inflate(R.layout.best_offers,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BestOffer app = best.get(position);

        Picasso.get().load(best.get(position).getImage()).into(holder.mImage);

    }

    @Override
    public int getItemCount() {
        return best.size();
    }


}