package com.example.plutoacademy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BooksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BooksFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BooksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BooksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BooksFragment newInstance(String param1, String param2) {
        BooksFragment fragment = new BooksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_books, container, false);

        ArrayList<BooksModel> BooksList = new ArrayList<>();

        BooksList.add(new BooksModel(R.drawable.bookitem, 25, "The Power Of Your Subconscious Mind" ));
        BooksList.add(new BooksModel(R.drawable.bookitem, 25, "The Power Of Your Subconscious Mind" ));
        BooksList.add(new BooksModel(R.drawable.bookitem, 25, "The Power Of Your Subconscious Mind" ));
        BooksList.add(new BooksModel(R.drawable.bookitem, 25, "The Power Of Your Subconscious Mind" ));
        BooksList.add(new BooksModel(R.drawable.bookitem, 25, "The Power Of Your Subconscious Mind" ));
        BooksList.add(new BooksModel(R.drawable.bookitem, 25, "The Power Of Your Subconscious Mind" ));
        BooksList.add(new BooksModel(R.drawable.bookitem, 25, "The Power Of Your Subconscious Mind" ));

        mRecyclerView = view.findViewById(R.id.BooksRecyclerView);
        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new GridLayoutManager(getActivity());
        mAdapter = new BooksAdapter(BooksList);
//        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        return view;

    }
}