package com.currencyapplication.currencyapplicatio.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.currencyapplication.currencyapplicatio.Adapter.TodayPriceAdapter;
import com.currencyapplication.currencyapplicatio.Adapter.TodayPriceShowItemAdapter;
import com.currencyapplication.currencyapplicatio.Models.TodayPriceItem;
import com.currencyapplication.currencyapplicatio.Models.TodayPriceShowItem;
import com.currencyapplication.currencyapplicatio.R;
import com.currencyapplication.currencyapplicatio.SplachActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TodayPriceFragment extends Fragment {

    RecyclerView recyclerViewShowSection;
    RecyclerView recyclerViewShowItems;

    RequestQueue referenceQueue;
    List<TodayPriceItem> todayPriceItems;
    TodayPriceAdapter todayPriceAdapter;

    List<TodayPriceShowItem> todayPriceShowItems;
    TodayPriceShowItemAdapter todayPriceShowItemAdapter;
    public static TextView lastUpdateTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_today_price, container, false);

        MobileAds.initialize(view.getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = new AdView(view.getContext());
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-7449694416039010/8446412211");

        AdView mAdView = view.findViewById(R.id.adViewTodayPrice);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mAdView.loadAd(adRequest);

        String url = SplachActivity.url;
        todayPriceItems = new ArrayList<>();
        todayPriceShowItems = new ArrayList<>();
        lastUpdateTv = view.findViewById(R.id.textViewTodayPrice);
        recyclerViewShowSection = view.findViewById(R.id.recyclerViewTodayPriceShowSection);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        recyclerViewShowSection.setLayoutManager(manager);

        recyclerViewShowItems = view.findViewById(R.id.recyclerViewTodayPriceShowItems);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerViewShowItems.setLayoutManager(layoutManager);

        referenceQueue = Volley.newRequestQueue(view.getContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int id = jsonObject.getInt("curr_id");
                                String name = jsonObject.getString("curr_name");
                                String last_mod = jsonObject.getString("last_mod");
                                lastUpdateTv.setText("أخر تحديث لأسعار الصرف " + last_mod);
                                todayPriceItems.add(new TodayPriceItem(id, name));
                                todayPriceAdapter = new TodayPriceAdapter(view.getContext(), todayPriceItems, recyclerViewShowItems);
                                recyclerViewShowSection.setAdapter(todayPriceAdapter);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "إتصل بالإنترنت, رجاءاُ.", Toast.LENGTH_SHORT).show();

            }
        });
        referenceQueue.add(request);


        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("data");
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String name1 = "المركزي";
                            int sale1 = jsonObject.getInt("c_sale");
                            int buy1 = jsonObject.getInt("c_buy");
                            int saleHistory1 = jsonObject.getInt("c_sale_history");
                            int buyHistory1 = jsonObject.getInt("c_buy_history");
                            int defValue1 = jsonObject.getInt("c_sale_history_def");
                            String historyStatus1 = jsonObject.getString("c_sale_history_status");

                            JSONArray jsonArrayStates = jsonObject.getJSONArray("states");
                            for (int i = 0; i < jsonArrayStates.length(); i++) {

                                JSONObject jsonObjectStates = jsonArrayStates.getJSONObject(i);
                                String stateName = jsonObjectStates.getString("state_name");
                                int sale = jsonObjectStates.getInt("b_sale");
                                int buy = jsonObjectStates.getInt("b_buy");
                                int saleHistory = jsonObjectStates.getInt("b_sale_history");
                                int buyHistory = jsonObjectStates.getInt("b_buy_history");
                                int defValue = jsonObjectStates.getInt("b_sale_history_def");
                                String historyStatus = jsonObjectStates.getString("b_sale_history_status");

                                todayPriceShowItems.add(new TodayPriceShowItem(stateName, sale, buy, saleHistory, buyHistory, defValue, historyStatus));
                                todayPriceShowItemAdapter = new TodayPriceShowItemAdapter(view.getContext(), todayPriceShowItems);
                                recyclerViewShowItems.setAdapter(todayPriceShowItemAdapter);
                                todayPriceShowItemAdapter.notifyDataSetChanged();

                            }
                            todayPriceShowItems.add(1, new TodayPriceShowItem(name1, sale1, buy1, saleHistory1, buyHistory1, defValue1, historyStatus1));
                            todayPriceShowItemAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "إتصل بالإنترنت, رجاءاُ.", Toast.LENGTH_SHORT).show();

            }
        });

        referenceQueue.add(request1);


        return view;
    }

}