package com.currencyapplication.currencyapplicatio.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.currencyapplication.currencyapplicatio.Adapter.PhoneAdapter;
import com.currencyapplication.currencyapplicatio.Models.ExchangeRateItem;
import com.currencyapplication.currencyapplicatio.Models.PhoneItem;
import com.currencyapplication.currencyapplicatio.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class ExchangeRatesFragment extends Fragment {


    RecyclerView recyclerView;
    List<ExchangeRateItem> exchangeRateItems;
    TextView lastUpdateTv;
    RequestQueue referenceQueue;
    Spinner spinnerPhone;
    ImageView phoneLogoImageView;

    PhoneAdapter phoneAdapter;
    List<PhoneItem> phoneItems;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_exchange_rates, container, false);

        MobileAds.initialize(view.getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = new AdView(view.getContext());
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-7449694416039010/7686199580");

        AdView mAdView = view.findViewById(R.id.adViewExchangeRates);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        referenceQueue = Volley.newRequestQueue(view.getContext());
        exchangeRateItems = new ArrayList<>();
        lastUpdateTv = view.findViewById(R.id.lastUpdateTv);
        lastUpdateTv.setText("لعرض اسعار الجوالات قد يلزم تشغيل vpn");

        recyclerView = view.findViewById(R.id.recyclerViewExchangeRates);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        spinnerPhone = view.findViewById(R.id.spinnerPhone);
        phoneLogoImageView = view.findViewById(R.id.phoneLogoImageView);

        phoneItems = new ArrayList<>();

        spinnerPhone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemSelected = spinnerPhone.getItemAtPosition(i).toString();
                showData(itemSelected);
                switch (itemSelected) {
                    case "Samsung":
                        phoneLogoImageView.setImageResource(R.drawable.samsung_logo);
                        break;
                    case "Xiaomi":
                        phoneLogoImageView.setImageResource(R.drawable.xiaomi_logo);
                        break;
                    case "Apple":
                        phoneLogoImageView.setImageResource(R.drawable.apple_logo);
                        break;
                    case "Huawei":
                        phoneLogoImageView.setImageResource(R.drawable.huawei_logo);
                        break;
                    case "Honor":
                        phoneLogoImageView.setImageResource(R.drawable.honor_logo);
                        break;
                    case "Oppo":
                        phoneLogoImageView.setImageResource(R.drawable.oppo_logo);
                        break;
                    case "Nokia":
                        phoneLogoImageView.setImageResource(R.drawable.nokia_logo);
                        break;
                    case "Realme":
                        phoneLogoImageView.setImageResource(R.drawable.realme_logo);
                        break;
                    case "Infinix":
                        phoneLogoImageView.setImageResource(R.drawable.infinix_logo);
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
// last update
        /*
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Date");
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    DateUpdate s =  snapshot.getValue(DateUpdate.class);
                    lastUpdateTv.setText(s.getDate()+"");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
*/
//        progressBar = view.findViewById(R.id.progress);


//        new Thread(new Runnable() {
//            public void run() {
//                while (progressStatus < 100) {
//                    progressStatus += 1;
//                    //Update progress bar with completion of operation
//                    handler.post(new Runnable() {
//                        public void run() {
//                            progressBar.setProgress(progressStatus);
////                            textView.setText(progressStatus+"/"+progressBar.getMax());
//                        }
//                    });
//                    try {
//                        // Sleep for 200 milliseconds.
//                        //Just to display the progress slowly
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();

        /*
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        String url = SplachActivity.url;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0 ; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int id = jsonObject.getInt("curr_id");
                                String name = jsonObject.getString("curr_name");
                                String last_mod = jsonObject.getString("last_mod");
                                lastUpdateTv.setText("أخر تحديث لأسعار الصرف "+last_mod);
                                JSONArray jsonArrayStates = jsonObject.getJSONArray("states");
                                    JSONObject jsonObjectStates = jsonArrayStates.getJSONObject(0);
                                        int sale = jsonObjectStates.getInt("b_sale");
                                        int buy = jsonObjectStates.getInt("b_buy");
                                        int defValue = jsonObjectStates.getInt("b_sale_history_def");
                                        String historyStatus = jsonObjectStates.getString("b_sale_history_status");
                                        exchangeRateItems.add(new ExchangeRateItem(id,name,sale,buy,defValue,historyStatus));
                                        exchangeRateAdapter = new ExchangeRateAdapter(view.getContext(),exchangeRateItems);
                                        recyclerView.setAdapter(exchangeRateAdapter);

                            }

                            exchangeRateItems.add(0 ,new ExchangeRateItem(1,"test",1,1,1,"d"));
                            exchangeRateItems.add(6 ,new ExchangeRateItem(1,"test",1,1,1,"d"));

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
*/

        return view;
    }

    private void showData(String nameSelected) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(nameSelected);
        Log.d("testF", "test1");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("testF", "test2");
                phoneItems.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Log.d("testF", "test3");
                    PhoneItem phoneItem = snapshot1.getValue(PhoneItem.class);
                    phoneItems.add(phoneItem);
                }
                Log.d("testF", "test4");
                phoneAdapter = new PhoneAdapter(getContext(), phoneItems);
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(phoneAdapter);
                phoneAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("testF", error.getMessage());

            }
        });
    }

}