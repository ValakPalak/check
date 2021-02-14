package com.example.plutoacademy;

public class BooksModel {
    private String mBookImage;
    private int mRecBook;
    private String mBookName;

    public String getmBookImage() {
        return mBookImage;
    }

    public int getmRecBook() {
        return mRecBook;
    }

    public String getmBookName() {
        return mBookName;
    }

    public BooksModel(String mBookImage, int mRecBook, String mBookName) {
        this.mBookImage = mBookImage;
        this.mRecBook = mRecBook;
        this.mBookName = mBookName;
    }
}
