package com.activator.quantsapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link APIFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class APIFragment extends Fragment {

    private RecyclerView recyclerView;
    private APIAdapter adapter;
    private Context mContext;
    private final String TAG = "APIFragment";

    private ArrayList<FeedModel> feedlist;

    public APIFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static APIFragment newInstance() {
        APIFragment fragment = new APIFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_api,container,false);
        mContext = this.requireActivity().getBaseContext();

        feedlist = new ArrayList<>();
        adapter = new APIAdapter(feedlist,mContext);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        getFeed();

        return root;
    }

    private void getFeed() {

        String url = "https://api.androidhive.info/feed/feed.json";

        com.android.volley.RequestQueue mRequestQueue = Volley.newRequestQueue(this.requireActivity());

        StringRequest feedRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    try {
                        JSONObject respJson = new JSONObject(response);

                        JSONArray feedArray = respJson.getJSONArray("feed");

                        for(int i=0;i<feedArray.length();i++){
                            JSONObject jsonObject = feedArray.getJSONObject(i);

                            //Log.d(TAG, "item at "+i+" --- "+jsonObject.toString());

                            int id = 0;
                            String name = "";
                            String status = "";
                            String profilePic = "";
                            String image = "";
                            long date = 0;
                            String url = "";
                            if(jsonObject.has("id")){
                                id = jsonObject.getInt("id");
                            }
                            if(jsonObject.has("name")){
                                name = jsonObject.getString("name");
                            }
                            if(jsonObject.has("image")){
                                image = jsonObject.getString("image");
                            }
                            if(jsonObject.has("status")){
                                status = jsonObject.getString("status");
                            }
                            if(jsonObject.has("profilePic")){
                                profilePic = jsonObject.getString("profilePic");
                            }
                            if(jsonObject.has("timeStamp")){
                                date = jsonObject.getLong("timeStamp");
                            }
                            if(jsonObject.has("url")){
                                url = jsonObject.getString("url");
                                //System.out.println(url);

                            }

                            FeedModel item = new FeedModel(id,name,image,status,profilePic,date,url);


                            feedlist.add(item);

                        }
                        System.out.println(feedlist.size());
                        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();

                    }
                    catch (Exception e){
                        Toast.makeText(mContext,"Error parsing JSON",Toast.LENGTH_SHORT).show();
                        Log.e(TAG,"Error parsing JSON : ",e);
                    }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG,"Error with networkRequest" +error.getLocalizedMessage());
                    }
                });

        feedRequest.setTag(TAG);
        int socketTimeout = 10000;//10 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        feedRequest.setRetryPolicy(policy);
        feedRequest.setShouldCache(false);
        RequestQueueSingleton.getInstance(requireActivity().getApplicationContext()).addToRequestQueue(feedRequest);


    }
}