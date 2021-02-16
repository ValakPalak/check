package com.example.plutoacademy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExpertsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpertsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExpertsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExpertsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExpertsFragment newInstance(String param1, String param2) {
        ExpertsFragment fragment = new ExpertsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    SearchView searchView;
    ArrayList<ExpertsModel> ExpertsList ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_experts, container, false);
        ExpertsList = new ArrayList<>();
        searchView=view.findViewById(R.id.searchExperts);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ExpertsList.clear();
                getDataOnSearch(newText);
                return false;
            }
        });

        /*BooksList.add(new BooksModel(R.drawable.bookitem, 25, "The Power Of Your Subconscious Mind" ));
        BooksList.add(new BooksModel(R.drawable.bookitem, 25, "The Power Of Your Subconscious Mind" ));
        BooksList.add(new BooksModel(R.drawable.bookitem, 25, "The Power Of Your Subconscious Mind" ));
        BooksList.add(new BooksModel(R.drawable.bookitem, 25, "The Power Of Your Subconscious Mind" ));
        BooksList.add(new BooksModel(R.drawable.bookitem, 25, "The Power Of Your Subconscious Mind" ));
        BooksList.add(new BooksModel(R.drawable.bookitem, 25, "The Power Of Your Subconscious Mind" ));
        BooksList.add(new BooksModel(R.drawable.bookitem, 25, "The Power Of Your Subconscious Mind" ));
*/
        mRecyclerView = view.findViewById(R.id.ExpertsRecyclerView);
        mRecyclerView.setHasFixedSize(true);

//        mLayoutManager = new GridLayoutManager(getActivity());
        getData();


        return view;
    }

    private void getData() {
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        String url="https://plutoacademy.in/api/experts/list?page=1";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Hello",response.toString());
                String image = null;
                String slug = null;
                String designation = null;
                try {
                    JSONObject jsonObject = new JSONObject(response);
//                    String title=jsonObject.getString("title");
                    JSONArray jsonArray = jsonObject.getJSONArray("experts");
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        image=jsonObject1.getString("mainImage");
                        //slug=jsonObject1.getString("slug");
                        String name=jsonObject1.getString("name");
                        designation=jsonObject1.getString("designation");
                        ExpertsList.add(new ExpertsModel(image, name, designation));

                    }
                    mAdapter = new ExpertsAdapter(ExpertsList);
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



    private void getDataOnSearch(String expert) {
        Toast.makeText(getContext(), ""+expert, Toast.LENGTH_SHORT).show();

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        String url="https://plutoacademy.in/api/experts/search?val=" + expert;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("data",response.toString());
                String image = null;
                String slug = null;
                String designation = null;
                try {
                    //TODO: if query parameter not null then this
                    JSONArray jsonArray = new JSONArray(response);

                    //TODO: if query paramerte is null then this
                    // JSONObject jsonObject=new JSONObject(response);
                    //JSONArray jsonArray=jsonObject.getJSONArray("experts");
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        image=jsonObject1.getString("mainImage");
                        //slug=jsonObject1.getString("slug");
                        String name=jsonObject1.getString("name");
                        designation=jsonObject1.getString("designation");
                        ExpertsList.add(new ExpertsModel(image, name, designation));

                    }
                    mAdapter = new ExpertsAdapter(ExpertsList);
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
}