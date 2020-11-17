package com.currencyapplication.currencyapplicatio.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.currencyapplication.currencyapplicatio.Models.PhoneItem;
import com.currencyapplication.currencyapplicatio.R;

import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder> {

    private Context context;
    private List<PhoneItem> phoneItems;

    public PhoneAdapter(Context context, List<PhoneItem> phoneItems) {
        this.context = context;
        this.phoneItems = phoneItems;
    }

    @NonNull
    @Override
    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phone_item_layout, parent, false);
        return new PhoneViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder holder, int position) {

        PhoneItem phoneItem = phoneItems.get(position);
        holder.phoneNameTv.setText(phoneItem.getPhoneName());
        holder.phoneScreenTv.setText(phoneItem.getPhoneScreen()+" in");
        holder.phoneCameraTv.setText(phoneItem.getPhoneCamera()+" MB");
        holder.phoneBattartTv.setText(phoneItem.getPhoneBattart()+" MAH");
        holder.phoneRatingTv.setText(phoneItem.getPhoneRating()+"%");
        holder.phoneSalaryTv.setText(phoneItem.getPhoneSalary());

    }

    @Override
    public int getItemCount() {
        return phoneItems.size();
    }

    class PhoneViewHolder extends RecyclerView.ViewHolder {

        private TextView phoneNameTv;
        private TextView phoneScreenTv;
        private TextView phoneCameraTv;
        private TextView phoneBattartTv;
        private TextView phoneRatingTv;
        private TextView phoneSalaryTv;

        public PhoneViewHolder(@NonNull View itemView) {
            super(itemView);

            phoneNameTv = itemView.findViewById(R.id.phoneNameTv);
            phoneScreenTv = itemView.findViewById(R.id.phoneScreenTv);
            phoneCameraTv = itemView.findViewById(R.id.phoneCameraTv);
            phoneBattartTv = itemView.findViewById(R.id.phoneBattartTv);
            phoneRatingTv = itemView.findViewById(R.id.phoneRatingTv);
            phoneSalaryTv = itemView.findViewById(R.id.phoneSalaryTv);
        }
    }
}
