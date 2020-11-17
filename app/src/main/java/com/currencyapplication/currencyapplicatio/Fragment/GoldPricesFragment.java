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
import com.currencyapplication.currencyapplicatio.Adapter.GoldPriceAdapter;
import com.currencyapplication.currencyapplicatio.Models.GoldPriceItem;
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

public class GoldPricesFragment extends Fragment {

    RecyclerView recyclerView;
    GoldPriceAdapter goldPriceAdapter;
    List<GoldPriceItem> goldPriceItems;
    public static TextView textView;

    RequestQueue referenceQueue;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_gold_prices, container, false);

        MobileAds.initialize(view.getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = new AdView(view.getContext());
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-7449694416039010/7099359300");

        adView = view.findViewById(R.id.adView00);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        adView.loadAd(adRequest);

        referenceQueue = Volley.newRequestQueue(view.getContext());
        goldPriceItems = new ArrayList<>();
        textView = view.findViewById(R.id.lastUpdateTvGoldPrices);
        readTime();
        recyclerView = view.findViewById(R.id.recyclerViewGoldPrices);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        String url = "http://syria-ex.com/mzMvc/mzApi/mzSyriaExpoApi/mzGoldData.php?license_key=app_key_136544876";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0 ; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                int gold_value = jsonObject.getInt("gold_value");
                                int gold_history = jsonObject.getInt("gold_history");
                                String gold_history_status = jsonObject.getString("gold_history_status");
                                int gold_history_def = jsonObject.getInt("gold_history_def");

                                goldPriceItems.add(new GoldPriceItem(id,gold_value,gold_history,gold_history_status,gold_history_def));
                                goldPriceAdapter = new GoldPriceAdapter(view.getContext(),goldPriceItems);
                                recyclerView.setAdapter(goldPriceAdapter);
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

        return view;
    }

    private void readTime(){
        String url = SplachActivity.url;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String last_mod = jsonObject.getString("last_mod");
                            textView.setText("أخر تحديث لأسعار الصرف " + last_mod);

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
    }

}