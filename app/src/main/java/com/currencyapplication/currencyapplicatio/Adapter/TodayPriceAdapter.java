package com.currencyapplication.currencyapplicatio.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.currencyapplication.currencyapplicatio.Models.TodayPriceItem;
import com.currencyapplication.currencyapplicatio.Models.TodayPriceShowItem;
import com.currencyapplication.currencyapplicatio.R;
import com.currencyapplication.currencyapplicatio.SplachActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TodayPriceAdapter extends RecyclerView.Adapter<TodayPriceAdapter.ViewHolder> {

    Context context;
    List<TodayPriceItem> todayPriceItems;
    RecyclerView recyclerView;
    String url = SplachActivity.url;
    int index;

    int n;

    public TodayPriceAdapter(Context context, List<TodayPriceItem> todayPriceItems, RecyclerView recyclerView) {
        this.context = context;
        this.todayPriceItems = todayPriceItems;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.today_price_show_section_item_layout, parent, false);
        return new TodayPriceAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final TodayPriceItem item = todayPriceItems.get(position);

        switch (item.getId()) {
            case 11:
                holder.cImage.setImageResource(R.drawable.image11);
                break;
            case 12:
                holder.cImage.setImageResource(R.drawable.image12);
                break;
            case 13:
                holder.cImage.setImageResource(R.drawable.image13);
                break;
            case 14:
                holder.cImage.setImageResource(R.drawable.image14);
                break;
            case 15:
                holder.cImage.setImageResource(R.drawable.image15);
                break;
            case 16:
                holder.cImage.setImageResource(R.drawable.image16);
                break;
            case 17:
                holder.cImage.setImageResource(R.drawable.image17);
                break;
            case 18:
                holder.cImage.setImageResource(R.drawable.image18);
                break;
            case 19:
                holder.cImage.setImageResource(R.drawable.image19);
                break;
            case 20:
                holder.cImage.setImageResource(R.drawable.image20);
                break;

        }
        holder.cName.setText(item.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                index = position;
                notifyDataSetChanged();

                switch (item.getId()) {
                    case 11:
                        n = 0;
                        showData(n);
                        break;
                    case 12:
                        n = 1;
                        showData(n);
                        break;
                    case 13:
                        n = 2;
                        showData(n);
                        break;
                    case 14:
                        n = 3;
                        showData(n);
                        break;
                    case 15:
                        n = 4;
                        showData(n);
                        break;
                    case 16:
                        n = 5;
                        showData(n);
                        break;
                    case 17:
                        n = 6;
                        showData(n);
                        break;
                    case 18:
                        n = 7;
                        showData(n);
                        break;
                    case 19:
                        n = 8;
                        showData(n);
                        break;
                    case 20:
                        n = 9;
                        showData(n);
                        break;

                }
            }
        });

        if (position == index) {
            holder.view.setBackground(context.getResources().getDrawable(R.drawable.exchange_rate_layout_bg2));
        } else {
            holder.view.setBackground(context.getResources().getDrawable(R.drawable.exchange_rate_layout_bg));
        }
    }

    @Override
    public int getItemCount() {
        return todayPriceItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView cImage;
        public TextView cName;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);

            cImage = itemView.findViewById(R.id.todayPriceImageItem);
            cName = itemView.findViewById(R.id.todayPriceNameItem);
            view = itemView.findViewById(R.id.view8);


        }
    }

    private void showData(final int position) {

        final List<TodayPriceShowItem> todayPriceShowItems = new ArrayList<>();
        final RequestQueue referenceQueue = Volley.newRequestQueue(context);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        final TodayPriceShowItemAdapter todayPriceShowItemAdapter = new TodayPriceShowItemAdapter(context, todayPriceShowItems);
        recyclerView.setAdapter(todayPriceShowItemAdapter);

        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("data");
                            JSONObject jsonObject = jsonArray.getJSONObject(position);
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
                                todayPriceShowItemAdapter.notifyDataSetChanged();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "إتصل بالإنترنت, رجاءاُ.", Toast.LENGTH_SHORT).show();

            }
        });


        referenceQueue.add(request1);

    }

}
