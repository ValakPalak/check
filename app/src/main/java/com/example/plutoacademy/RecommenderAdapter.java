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

public class RecommenderAdapter extends RecyclerView.Adapter<RecommenderAdapter.RecommenderViewHolder>{
    public ArrayList<RecommenderModel> RecommenderList;

    public static class RecommenderViewHolder extends RecyclerView.ViewHolder {
        public TextView mRecName;
        public TextView mRecDes;
        public TextView mSource;
        public RecommenderViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecName = itemView.findViewById(R.id.RecName);
            mRecDes = itemView.findViewById(R.id.recdes);
            mSource = itemView.findViewById(R.id.ViewSource);
        }
    }

    @NonNull
    @Override
    public RecommenderAdapter.RecommenderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookdetails_item, parent, false);
        return new RecommenderAdapter.RecommenderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommenderAdapter.RecommenderViewHolder holder, int position) {
        RecommenderModel currItem = RecommenderList.get(position);

        holder.mRecName.setText(currItem.getmRecName()+"");
        holder.mRecDes.setText(currItem.getmRecDes()+"");
        holder.mSource.setText(currItem.getmSource()+"");

    }

    @Override
    public int getItemCount() {
        return RecommenderList.size();
    }

    public RecommenderAdapter(ArrayList<RecommenderModel> RecommenderList){
        this.RecommenderList = RecommenderList;
    }

}
