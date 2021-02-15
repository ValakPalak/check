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

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder> {
    public ArrayList<BooksModel> BooksList;

    public static class BooksViewHolder extends RecyclerView.ViewHolder {
        public ImageView mBookImage;
        public TextView mRecBook;
        public TextView mBookName;
        public BooksViewHolder(@NonNull View itemView) {
            super(itemView);
            mBookImage = itemView.findViewById(R.id.BookItemImageView);
            mRecBook = itemView.findViewById(R.id.NoRecBooks);
            mBookName = itemView.findViewById(R.id.BookNameTextView);
        }
    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.books_item, parent, false);
        return new BooksViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder holder, int position) {
        BooksModel currItem = BooksList.get(position);

        Picasso.get()
                .load(currItem.getmBookImage()).into(holder.mBookImage);
        holder.mRecBook.setText(currItem.getmRecBook()+"");
        holder.mBookName.setText(currItem.getmBookName()+"");

    }

    @Override
    public int getItemCount() {
        return BooksList.size();
    }

    public BooksAdapter(ArrayList<BooksModel> BooksList){
        this.BooksList = BooksList;
    }

}
