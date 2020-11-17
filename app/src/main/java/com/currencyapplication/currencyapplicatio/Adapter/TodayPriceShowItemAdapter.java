package com.currencyapplication.currencyapplicatio.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.currencyapplication.currencyapplicatio.Models.TodayPriceShowItem;
import com.currencyapplication.currencyapplicatio.R;
import com.google.android.gms.ads.AdView;

import java.util.List;

public class TodayPriceShowItemAdapter extends RecyclerView.Adapter<TodayPriceShowItemAdapter.ViewHolder> {


    Context context;
    private List<TodayPriceShowItem> todayPriceShowItems;

    public TodayPriceShowItemAdapter(Context context, List<TodayPriceShowItem> todayPriceShowItems) {
        this.context = context;
        this.todayPriceShowItems = todayPriceShowItems;
    }

    @NonNull
    @Override
    public TodayPriceShowItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.today_price_item_layout, parent, false);
        return new ViewHolder(view);
    }


    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TodayPriceShowItem item = todayPriceShowItems.get(position);
        holder.name.setText(item.getName());
        holder.saleValue.setText(item.getSaleValue() + "");
        holder.buyValue.setText(item.getBuyValue() + "");
        holder.lastValue.setText(item.getLastValueSale() + "");
        holder.defValue.setText(item.getLastValueBuy() + "");
        holder.historyValue.setText(item.getHistoryValue() + "");
        if (item.getHistoryStatus().equals("d")) {
            holder.historyStauts.setImageResource(R.drawable.arrow_red);
            holder.historyValue.setTextColor(context.getResources().getColor(R.color.red));
        } else {
            holder.historyStauts.setImageResource(R.drawable.arrow_green);
            holder.historyValue.setTextColor(context.getResources().getColor(R.color.green));
        }
    }

    @Override
    public int getItemCount() {
        return todayPriceShowItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView lastValue;
        TextView defValue;
        TextView saleValue;
        TextView buyValue;
        ImageView historyStauts;
        TextView historyValue;

        public ViewHolder(View itemView) {
            super(itemView);
            if (!(itemView instanceof AdView)) {

                name = itemView.findViewById(R.id.nameItemTodayPriceTv);
                lastValue = itemView.findViewById(R.id.lastValueTodayPriceTv);
                defValue = itemView.findViewById(R.id.defValueTodayPriceTv);
                saleValue = itemView.findViewById(R.id.saleValueTodayPriceTv);
                buyValue = itemView.findViewById(R.id.buyValueTodayPriceTv);
                historyStauts = itemView.findViewById(R.id.imageStatusTodayPriceIV);
                historyValue = itemView.findViewById(R.id.statusValueTodayPriceTv);
            }
        }
    }

}
