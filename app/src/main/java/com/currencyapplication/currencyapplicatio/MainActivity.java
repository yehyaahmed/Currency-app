package com.currencyapplication.currencyapplicatio;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.currencyapplication.currencyapplicatio.Fragment.CurrencyExchangeFragment;
import com.currencyapplication.currencyapplicatio.Fragment.ExchangeRatesFragment;
import com.currencyapplication.currencyapplicatio.Fragment.GoldPricesFragment;
import com.currencyapplication.currencyapplicatio.Fragment.TodayPriceFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;
    Fragment fragment;
    private DrawerLayout dl;
    private ActionBarDrawerToggle drawerToggle;

    TextView input, signBox;
    String sign, value1, value2;
    Double num1, num2, result;
    boolean hasDot;
    Menu menu;
    Switch switch1, switch2;
    boolean flag2;
    boolean flag3;
    RequestQueue referenceQueue;

    public static final String sw1 = "swithc1";
    public static final String sw2 = "swithc2";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static int gold_value, def;

    @SuppressLint("CommitPrefEdits")
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocale("ar");
        setContentView(R.layout.activity_main);
        Common.currenToken = FirebaseInstanceId.getInstance().getToken();
        System.out.println("Log Token: " + Common.currenToken);


//        FirebaseInstanceId.getInstance().getInstanceId()
//                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
//                        if (!task.isSuccessful()) {
//                            Log.w("TAG", "getInstanceId failed", task.getException());
//                            return;
//                        }
//
//                        // Get new Instance ID token
//                        String token = task.getResult().getToken();
//
//                        // Log and toast
//                        String msg = getString(R.string.common_open_on_phone, token);
//                        Log.d("", msg);
//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//                    }
//                });
//

        MobileAds.initialize(MainActivity.this, "ca-app-pub-6238785883059595~4969226899");

        dl = findViewById(R.id.dl);
        flag2 = true;
        referenceQueue = Volley.newRequestQueue(MainActivity.this);
        sharedPreferences = getSharedPreferences("Switch", MODE_PRIVATE);
        editor = sharedPreferences.edit();


        String url = SplachActivity.url;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            JSONArray jsonArrayStates = jsonObject.getJSONArray("states");
                            JSONObject jsonObjectStates = jsonArrayStates.getJSONObject(0);
                            def = jsonObjectStates.getInt("b_sale_history_def");

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

        String url11 = "http://syria-ex.com/mzMvc/mzApi/mzSyriaExpoApi/mzGoldData.php?license_key=app_key_136544876";

        JsonObjectRequest request11 = new JsonObjectRequest(Request.Method.GET, url11, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            JSONObject jsonObject = jsonArray.getJSONObject(1);
                            gold_value = jsonObject.getInt("gold_value");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        referenceQueue.add(request11);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.grey)));
        getSupportActionBar().setTitle("أسعار العملات");

        chipNavigationBar = findViewById(R.id.chipNavigationBar);
        chipNavigationBar.setItemSelected(R.id.todayPrice, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2, new TodayPriceFragment()).commit();

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.todayPrice:
                        fragment = new TodayPriceFragment();
                        flag2 = true;
                        flag3 = false;
                        break;

                    case R.id.exchangeRates:
                        fragment = new ExchangeRatesFragment();
                        break;

                    case R.id.goldPrices:
                        fragment = new GoldPricesFragment();
                        flag3 = true;
                        flag2 = false;
                        break;

                    case R.id.currencyExchange:
                        fragment = new CurrencyExchangeFragment();
                        flag3 = false;
                        flag2 = false;
                        break;
                }
                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2, fragment).commit();
                }
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        drawerToggle.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.new_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                switch (id) {

                    case R.id.notificationNav:
                        showNotification();
                        break;

                    case R.id.shareAppNav:
                        Intent intent1 = new Intent(Intent.ACTION_SEND);
                        intent1.setType("text/plain");
                        String shareSub = "تطبيق أسعار العملات";
                        String shareBady = "https://play.google.com/store/apps/details?id=com.currencyapplication.currencyapplication";
                        intent1.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                        intent1.putExtra(Intent.EXTRA_TEXT, shareBady);
                        startActivity(Intent.createChooser(intent1, "شارك التطبيق"));
                        break;

                    case R.id.evaluationAppNav:
                        Intent evaluationAppNav = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.currencyapplication.currencyapplication"));
                        startActivity(evaluationAppNav);
                        break;

                    case R.id.facebookNav:
                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Syria.exchange"));
                            startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Syria.exchange"));
                            startActivity(intent);
                        }
                        break;

                    case R.id.telegramNav:
                        Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/s/Syriaexchange"));
                        startActivity(telegram);
                        break;

                    case R.id.MessengerNav:
                        Intent massenger = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.messenger.com/t/Syria.exchange"));
                        startActivity(massenger);
                        break;
                }
                return true;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.calcolateNav:
                showCalcolate();
                break;
            case R.id.refrechDataNav:
                if (flag2) {
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
                                        TodayPriceFragment.lastUpdateTv.setText("أخر تحديث لأسعار الصرف " + last_mod);
                                        Toast.makeText(MainActivity.this, "تم التحديث", Toast.LENGTH_SHORT).show();

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

                if (flag3) {

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
                                        GoldPricesFragment.textView.setText("أخر تحديث لأسعار الصرف " + last_mod);
                                        Toast.makeText(MainActivity.this, "تم التحديث", Toast.LENGTH_SHORT).show();

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

                break;
        }

        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        this.menu = menu;
        return true;
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());

    }

    private void showCalcolate() {

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.show_calcolate_layout);

        input = dialog.findViewById(R.id.input);
        signBox = dialog.findViewById(R.id.sign);


        dialog.show();

    }

    private void showNotification() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.notification_layout);

        switch1 = dialog.findViewById(R.id.switch1);
        switch2 = dialog.findViewById(R.id.switch2);
        switch1.setChecked(sharedPreferences.getBoolean(sw1, true));
        switch2.setChecked(sharedPreferences.getBoolean(sw2, true));

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    updateSwitch1(true);
                } else {
                    updateSwitch1(false);
                }
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    updateSwitch2(true);
                } else {
                    updateSwitch2(false);
                }
            }
        });

        dialog.show();
    }

    private void updateSwitch1(boolean b) {
        editor.putBoolean(sw1, b);
        editor.apply();

    }

    private void updateSwitch2(boolean b) {
        editor.putBoolean(sw2, b);
        editor.apply();

    }

    @SuppressLint("SetTextI18n")
    public void btnClick_0(View view) {
        input.setText(input.getText() + "0");
    }

    @SuppressLint("SetTextI18n")
    public void btnClick_1(View view) {
        input.setText(input.getText() + "1");
    }

    @SuppressLint("SetTextI18n")
    public void btnClick_2(View view) {
        input.setText(input.getText() + "2");
    }

    @SuppressLint("SetTextI18n")
    public void btnClick_3(View view) {
        input.setText(input.getText() + "3");
    }

    @SuppressLint("SetTextI18n")
    public void btnClick_4(View view) {
        input.setText(input.getText() + "4");
    }

    @SuppressLint("SetTextI18n")
    public void btnClick_5(View view) {
        input.setText(input.getText() + "5");
    }

    @SuppressLint("SetTextI18n")
    public void btnClick_6(View view) {
        input.setText(input.getText() + "6");
    }

    @SuppressLint("SetTextI18n")
    public void btnClick_7(View view) {
        input.setText(input.getText() + "7");
    }

    @SuppressLint("SetTextI18n")
    public void btnClick_8(View view) {
        input.setText(input.getText() + "8");
    }

    @SuppressLint("SetTextI18n")
    public void btnClick_9(View view) {
        input.setText(input.getText() + "9");
    }

    public void btnClick_add(View view) {
        sign = "+";
        value1 = input.getText().toString();
        input.setText(null);
        signBox.setText("+");
        hasDot = false;
    }

    public void btnClick_subtract(View view) {
        sign = "-";
        value1 = input.getText().toString();
        input.setText(null);
        signBox.setText("-");
        hasDot = false;
    }

    public void btnClick_multiply(View view) {
        sign = "*";
        value1 = input.getText().toString();
        input.setText(null);
        signBox.setText("×");
        hasDot = false;
    }

    public void btnClick_divide(View view) {
        sign = "/";
        value1 = input.getText().toString();
        input.setText(null);
        signBox.setText("÷");
        hasDot = false;
    }

    @SuppressLint("SetTextI18n")
    public void btnClick_percentage(View view) {
        value1 = input.getText().toString();
        num1 = Double.parseDouble(value1);
        double result = (num1 / 100.0f) * 10;
        input.setText(result + "");
        sign = null;
        signBox.setText(null);
    }

    public void btnClick_clear(View view) {

        input.setText(null);
        signBox.setText(null);
        value1 = null;
        value2 = null;
        sign = null;
        hasDot = false;
    }

    @SuppressLint("SetTextI18n")
    public void btnClick_equal(View view) {
        if (sign == null) {
            signBox.setText("Error!");
        } else if (input.getText().equals("")) {
            signBox.setText("Error!");
        } else if ((sign.equals("+") || sign.equals("-") || sign.equals("*") || sign.equals("/")) && value1.equals("")) {
            signBox.setText("Error!");
        } else {
            switch (sign) {
                default:
                    break;
                case "+":
                    value2 = input.getText().toString();
                    num1 = Double.parseDouble(value1);
                    num2 = Double.parseDouble(value2);
                    result = num1 + num2;
                    input.setText(result + "");
                    sign = null;
                    signBox.setText(null);
                    break;
                case "-":
                    value2 = input.getText().toString();
                    num1 = Double.parseDouble(value1);
                    num2 = Double.parseDouble(value2);
                    result = num1 - num2;
                    input.setText(result + "");
                    sign = null;
                    signBox.setText(null);
                    break;
                case "*":
                    value2 = input.getText().toString();
                    num1 = Double.parseDouble(value1);
                    num2 = Double.parseDouble(value2);
                    result = num1 * num2;
                    input.setText(result + "");
                    sign = null;
                    signBox.setText(null);
                    break;
                case "/":
                    value2 = input.getText().toString();
                    num1 = Double.parseDouble(value1);
                    num2 = Double.parseDouble(value2);
                    result = num1 / num2;
                    input.setText(result + "");
                    sign = null;
                    signBox.setText(null);
                    break;
            }

        }
    }


    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("هل تريد الخروج من التطبيق");
        builder.setPositiveButton("خروج", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        builder.setNegativeButton("تقييم التطبيق", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Intent evaluationAppNav = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.currencyapplication.currencyapplication"));
                startActivity(evaluationAppNav);

            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();
        Button button = dialog.getButton(Dialog.BUTTON_POSITIVE);
        Button button1 = dialog.getButton(Dialog.BUTTON_NEGATIVE);
        button.setTextColor(Color.BLACK);
        button1.setTextColor(Color.BLACK);
    }
}