package com.currencyapplication.currencyapplicatio.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.currencyapplication.currencyapplicatio.Models.ExchangeRateItem;
import com.currencyapplication.currencyapplicatio.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.List;

public class ExchangeRateAdapter extends RecyclerView.Adapter<ExchangeRateAdapter.ViewHolder> {

    private Context context;
    private List<ExchangeRateItem> exchangeRateItems;
    int AD_TYPE = 0;
    int CONTENT_TYPE = 1;

    public ExchangeRateAdapter(Context context, List<ExchangeRateItem> exchangeRateItems) {
        this.context = context;
        this.exchangeRateItems = exchangeRateItems;
    }

    @NonNull
    @Override
    public ExchangeRateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdView adview;
        ExchangeRateAdapter.ViewHolder holder;
        if (viewType == AD_TYPE) {
            adview = new AdView(context);
            adview.setAdSize(AdSize.BANNER);

            // this is the good adview
            adview.setAdUnitId("ca-app-pub-7449694416039010/7104236925");

            float density = context.getResources().getDisplayMetrics().density;
            int height = Math.round(AdSize.BANNER.getHeight() * density);
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, height);
            adview.setLayoutParams(params);

            // dont use below if testing on a device
            // follow https://developers.google.com/admob/android/quick-start?hl=en to setup testing device
            AdRequest request = new AdRequest.Builder().build();
            adview.loadAd(request);
            holder = new ExchangeRateAdapter.ViewHolder(adview);

        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.exchange_rate_item_layout, parent, false);
            return new ExchangeRateAdapter.ViewHolder(view);
        }
        return holder;
    }

    @Override
    public int getItemViewType(int position) {

        if (position % 6 == 0)
            return AD_TYPE;
        return CONTENT_TYPE;
    }

    @SuppressLint({"SetTextI18n", "ResourceType"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (position % 6 != 0) {
            ExchangeRateItem item = exchangeRateItems.get(position);

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
            if (item.getHistory_status().equals("d")) {
                holder.cImageDef.setImageResource(R.drawable.arrow_red);
                holder.cDef.setTextColor(context.getResources().getColor(R.color.red));
            } else {
                holder.cImageDef.setImageResource(R.drawable.arrow_green);
                holder.cDef.setTextColor(context.getResources().getColor(R.color.green));
            }

            holder.cDef.setText(item.getHistory_value() + "");
            holder.cSale.setText(item.getSale() + "");
            holder.cBuy.setText(item.getBuy() + "");
        }
    }

    @Override
    public int getItemCount() {
        return exchangeRateItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView cImage;
        public TextView cName;
        public ImageView cImageDef;
        public TextView cDef;
        public TextView cSale;
        public TextView cBuy;

        public ViewHolder(View itemView) {
            super(itemView);

            cImage = itemView.findViewById(R.id.c_imageImageView);
            cName = itemView.findViewById(R.id.c_nameTv);
            cImageDef = itemView.findViewById(R.id.c_rateImageView);
            cDef = itemView.findViewById(R.id.c_defTv);
            cSale = itemView.findViewById(R.id.c_saleTv);
            cBuy = itemView.findViewById(R.id.c_buyTv);


        }
    }
}
