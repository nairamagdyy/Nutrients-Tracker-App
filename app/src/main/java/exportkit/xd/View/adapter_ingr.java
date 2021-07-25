package exportkit.xd.View;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import exportkit.xd.R;
import exportkit.xd.View.Profile.IProfile;

public class adapter_ingr extends RecyclerView.Adapter<adapter_ingr.ViewHolder>{

    List<String> ingredient;
    List<Integer> ids;
    LayoutInflater inflater;
    IProfile view;

    public adapter_ingr(Context ctx, List<Integer> ids, List<String> ingredient){
        this.ingredient = ingredient;
        this.ids= ids;
        this.inflater = LayoutInflater.from(ctx);
        this.view= (IProfile) ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.ingredients,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ingredient_name.setText(ingredient.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredient.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView ingredient_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredient_name = itemView.findViewById(R.id.igred_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    view.viewRecipeDetails(ids.get(getAdapterPosition()));
                }
            });
        }
    }
}