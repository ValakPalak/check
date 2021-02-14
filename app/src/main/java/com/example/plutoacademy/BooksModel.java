package com.example.plutoacademy;

public class BooksModel {
    private int mBookImage;
    private int mRecBook;
    private String mBookName;

    public BooksModel(int BookImage, int RecBook, String BookName) {
        this.mBookImage = BookImage;
        this.mRecBook = RecBook;
        this.mBookName = BookName;
    }

    public int getBookImage() {
        return mBookImage;
    }

    public int getRecBook() {
        return mRecBook;
    }

    public String getBookName() {
        return mBookName;
    }
}
