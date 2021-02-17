package com.example.plutoacademy;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.plutoacademy.databinding.LoadingdialogBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BooksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BooksFragment extends Fragment implements  OnItemClick {

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
    SearchView searchView;
    ArrayList<BooksModel> BooksList ;
    Dialog dialog;

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
        BooksList = new ArrayList<>();
        searchView=view.findViewById(R.id.searchBooks);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                BooksList.clear();
                getDataOnSearch(newText);
                return false;
            }
        });

        /*BooksList.add(new BooksModel(R.drawable.bookitem, 25, "The Power Of Your Subconscious Mind" ));
        BooksList.add(new BooksModel(R.drawable.bookitem, 25, "The Power Of Your Subconscious Mind" ));
*/
        mRecyclerView = view.findViewById(R.id.BooksRecyclerView);
        mRecyclerView.setHasFixedSize(true);

//        mLayoutManager = new GridLayoutManager(getActivity());
        getData();


        return view;
    }

    private void getData() {
        showLoadingDialog();
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        String url="https://plutoacademy.in/api/books/list?page=1";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Hello",response.toString());
                String image = null;
                String buy = null;
                int size = 0;
                try {
                    JSONObject jsonObject = new JSONObject(response);
//                    String title=jsonObject.getString("title");
                    JSONArray jsonArray = jsonObject.getJSONArray("books");
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                         image=jsonObject1.getString("mainImage");
                         buy=jsonObject1.getString("buy");
                        String title=jsonObject1.getString("title");
                        if(!jsonObject1.isNull("recommenders")) {
                            JSONArray recommenders = jsonObject1.getJSONArray("recommenders");
                            size = recommenders.length();
                        }
                        if(size < 10) size = 10;
                        else if (size>10 && size <25) size = 10;
                        else if(size>25 && size<50) size = 25;
                        else if(size>50 && size<100) size = 25;
                        else size = 100;
                        BooksList.add(new BooksModel(image, size, title));

                    }
                    mAdapter = new BooksAdapter(BooksList,BooksFragment.this);
                    mRecyclerView.setAdapter(mAdapter);
                    hideLoadingDialog();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void getDataOnSearch(String book) {
        Toast.makeText(getContext(), ""+book, Toast.LENGTH_SHORT).show();

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        String url="https://plutoacademy.in/api/books/search?val=" + book;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("data",response.toString());
                String image=null;

                String buy=null;
                int size=0;
                try {
                    //TODO: if query parameter not null then this
                    JSONArray jsonArray = new JSONArray(response);

                    //TODO: if query paramerte is null then this
                    // JSONObject jsonObject=new JSONObject(response);
                    //JSONArray jsonArray=jsonObject.getJSONArray("books");
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        image=jsonObject1.getString("mainImage");
                        buy=jsonObject1.getString("buy");
                        String title=jsonObject1.getString("title");
                        if(!jsonObject1.isNull("recommenders")) {
                            JSONArray recommenders = jsonObject1.getJSONArray("recommenders");
                            size = recommenders.length();
                        }
                        BooksList.add(new BooksModel(image, size, title));
                    }
                    mAdapter = new BooksAdapter(BooksList,BooksFragment.this);
                    mRecyclerView.setAdapter(mAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    public void showLoadingDialog() {

        LoadingdialogBinding binding = LoadingdialogBinding.inflate(LayoutInflater.from(getActivity()));

        if (dialog != null && dialog.isShowing())
            return;

        dialog = new Dialog(getActivity(), R.style.LoaderStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(binding.getRoot());

        if (dialog.getWindow() == null)
            return;

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }
    public void hideLoadingDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void Onclick(int position) {
    startActivity(new Intent(getContext(),BookDescription.class));
    }
}
