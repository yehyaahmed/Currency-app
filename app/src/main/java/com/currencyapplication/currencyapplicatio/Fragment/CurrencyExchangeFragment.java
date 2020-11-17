package com.currencyapplication.currencyapplicatio.Fragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
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

public class CurrencyExchangeFragment extends Fragment {

    String selectedItemSpinner1;
    String selectedItemSpinner2;
    int spinner1Sale;
    int spinner1Buy;
    int spinner2Sale;
    int spinner2Buy;
    int dollarSale;
    int dollarBuy;

    Spinner spinner1;
    Spinner spinner2;
    Button calculationBtn;
    EditText value;
    TextView resultTv,resultSaleTv,resultBuyTv;

    RequestQueue referenceQueue;

    String url;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency_exchange, container, false);

        MobileAds.initialize(view.getContext(),"ca-app-pub-7449694416039010~5357214441");

        MobileAds.initialize(view.getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = new AdView(view.getContext());
        adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
        adView.setAdUnitId("ca-app-pub-7449694416039010/7099359300");

        adView = view.findViewById(R.id.adView123);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        adView.loadAd(adRequest);


        referenceQueue = Volley.newRequestQueue(view.getContext());
        url = SplachActivity.url;

        spinner1 = view.findViewById(R.id.spinnerOne);
        spinner2 = view.findViewById(R.id.spinnerTwo);
        calculationBtn = view.findViewById(R.id.calculationBtn);
        value = view.findViewById(R.id.enterValueTv);
        resultTv = view.findViewById(R.id.resultTv);
        resultSaleTv = view.findViewById(R.id.resultSaleTv);
        resultBuyTv = view.findViewById(R.id.resultBuyTv);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItemSpinner1 = spinner1.getItemAtPosition(i).toString();
                fatchDataSpinner1(selectedItemSpinner1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItemSpinner2 = spinner2.getItemAtPosition(i).toString();
                fatchDataSpinner2(selectedItemSpinner2);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        fatchData();

        calculationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!value.getText().toString().matches("")){
                    if (!selectedItemSpinner1.equals("اختر نوع العملة") && !selectedItemSpinner2.equals("اختر نوع العملة") ) {

                        getResult(Integer.parseInt(value.getText().toString()));
                    }else {
                        Toast.makeText(view.getContext(), "إختر نوع العملة, رجاءاً", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(view.getContext(), "أدخل القيمة, رجاءاً", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    private void fatchDataSpinner2(final String spinner2String){
        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0 ; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String name = jsonObject.getString("curr_name");
                                if (name.equals(spinner2String)){
                                    JSONArray jsonArrayStates = jsonObject.getJSONArray("states");
                                    JSONObject jsonObjectStates = jsonArrayStates.getJSONObject(0);
                                    spinner2Sale = jsonObjectStates.getInt("b_sale");
                                    spinner2Buy = jsonObjectStates.getInt("b_buy");

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        referenceQueue.add(request1);

    }

    private void fatchDataSpinner1(final String spinner1String){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0 ; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String name = jsonObject.getString("curr_name");
                                if (name.equals(spinner1String)){
                                    JSONArray jsonArrayStates = jsonObject.getJSONArray("states");
                                    JSONObject jsonObjectStates = jsonArrayStates.getJSONObject(0);
                                    spinner1Sale = jsonObjectStates.getInt("b_sale");
                                    spinner1Buy = jsonObjectStates.getInt("b_buy");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        referenceQueue.add(request);

    }

    private void fatchData(){

        JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0 ; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String name = jsonObject.getString("curr_name");
                                if (name.equals("الدولار الامريكي")){
                                    JSONArray jsonArrayStates = jsonObject.getJSONArray("states");
                                    JSONObject jsonObjectStates = jsonArrayStates.getJSONObject(0);
                                    dollarSale = jsonObjectStates.getInt("b_sale");
                                    dollarBuy = jsonObjectStates.getInt("b_buy");

                                }
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

        referenceQueue.add(request2);

    }

    @SuppressLint("SetTextI18n")
    private void getResult(int value){

        double resultDollarD = (dollarSale+dollarBuy)/2;
        double resultSpinner1D = (spinner1Sale+spinner1Buy)/2;
        double resultSpinner2D = (spinner2Sale+spinner2Buy)/2;

        double result4D = resultSpinner1D/resultDollarD;
        double result5D = resultSpinner2D/resultDollarD;

        double finalResultD = (value*result4D)/result5D;

        int finalResultSale =  (value * spinner1Sale);
        int finalResultBuy = (value * spinner1Buy);

        finalResultD = Math.round(finalResultD*1000.0)/1000.0;


            resultTv.setText(finalResultD+"");
            resultSaleTv.setText(finalResultSale+"");
            resultBuyTv.setText(finalResultBuy+"");

    }

}