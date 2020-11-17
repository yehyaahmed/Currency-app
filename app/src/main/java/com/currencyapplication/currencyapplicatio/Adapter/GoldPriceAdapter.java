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

import com.currencyapplication.currencyapplicatio.Models.GoldPriceItem;
import com.currencyapplication.currencyapplicatio.R;

import java.util.List;

public class GoldPriceAdapter extends RecyclerView.Adapter<GoldPriceAdapter.ViewHolder> {

    private Context context;
    private List<GoldPriceItem> goldPriceItems;


    public GoldPriceAdapter(Context context, List<GoldPriceItem> goldPriceItems){
        this.context = context;
        this.goldPriceItems = goldPriceItems;
    }

    @NonNull
    @Override
    public GoldPriceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            View view = LayoutInflater.from(context).inflate(R.layout.gold_prices_item_layout,parent,false);
            return new GoldPriceAdapter.ViewHolder(view);

    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            GoldPriceItem item = goldPriceItems.get(position);

            switch (item.getId()) {
                case 6:
                    holder.goldName.setText("غرام الذهب عيار 21");
                    break;
                case 7:
                    holder.goldName.setText("غرام الذهب عيار 22");
                    break;
                case 8:
                    holder.goldName.setText("غرام الذهب عيار 18");
                    break;
                case 9:
                    holder.goldName.setText("أونصة عيار 24");
                    break;
                case 10:
                    holder.goldName.setText("ليرة عيار 21");
                    break;
            }
            if (item.getGold_history_status().equals("d")) {
                holder.goldHistoryStatus.setImageResource(R.drawable.arrow_red);
            } else {
                holder.goldHistoryStatus.setImageResource(R.drawable.arrow_green);
            }
            holder.goldValue.setText(item.getGold_value() + "");
            holder.goldHistory.setText(item.getGold_history() + "");
            holder.goldHistoryDef.setText(item.getGold_history_def() + "");

    }

    @Override
    public int getItemCount() {
        return goldPriceItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView goldName;
        public TextView goldValue;
        public TextView goldHistory;
        public TextView goldHistoryDef;
        public ImageView goldHistoryStatus;

        public ViewHolder(View itemView){
            super(itemView);

            goldName = itemView.findViewById(R.id.gold_nameTv);
            goldValue = itemView.findViewById(R.id.gold_valueTv);
            goldHistory = itemView.findViewById(R.id.gold_historyTv);
            goldHistoryDef = itemView.findViewById(R.id.gold_history_defTv);
            goldHistoryStatus = itemView.findViewById(R.id.gold_history_statusImageView);


        }
    }
}
