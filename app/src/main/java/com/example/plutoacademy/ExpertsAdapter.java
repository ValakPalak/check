package com.example.plutoacademy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExpertsAdapter extends RecyclerView.Adapter<ExpertsAdapter.ExpertsViewHolder> {
    public ArrayList<ExpertsModel> ExpertsList;

    public static class ExpertsViewHolder extends RecyclerView.ViewHolder {
        public ImageView mExpertImage;
        public TextView mExpertName;
        public TextView mExpertType;
        public ExpertsViewHolder(@NonNull View itemView) {
            super(itemView);
            mExpertImage = itemView.findViewById(R.id.ExpertItemImageView);
            mExpertName = itemView.findViewById(R.id.ExpertName);
            mExpertType = itemView.findViewById(R.id.ExpertType);
        }
    }

    @NonNull
    @Override
    public ExpertsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.experts_item, parent, false);
        return new ExpertsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpertsViewHolder holder, int position) {
        ExpertsModel currItem = ExpertsList.get(position);

        Picasso.get()
                .load(currItem.getmExpertImage()).into(holder.mExpertImage);
        holder.mExpertName.setText(currItem.getmExpertName()+"");
        holder.mExpertType.setText(currItem.getmExpertType()+"");

    }

    @Override
    public int getItemCount() {
        return ExpertsList.size();
    }

    public ExpertsAdapter(ArrayList<ExpertsModel> ExpertsList){
        this.ExpertsList = ExpertsList;
    }

}
