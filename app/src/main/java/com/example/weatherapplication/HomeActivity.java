package com.example.weatherapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapplication.Retrofit.ApiClient;
import com.example.weatherapplication.Retrofit.ApiInterface;
import com.example.weatherapplication.Retrofit.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    ImageView btnSearch;
    TextView txtTemp, txtDesc, txtHum;
    EditText searchField;
    Button butt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnSearch = findViewById(R.id.btnSearch);
        txtTemp = findViewById(R.id.txtTemp);
        txtDesc = findViewById(R.id.txtDesc);
        txtHum = findViewById(R.id.txtHum);
        searchField = findViewById(R.id.searchField);
        butt = findViewById(R.id.button);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getWeatherData(searchField.getText().toString().trim());

            }
        });

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeatherData(searchField.getText().toString().trim());
            }
        });


    }

    private void getWeatherData(String name){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<Example> call = apiInterface.getWeatherData(name);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {


                txtTemp.setText(response.body().getMain().getTemp());
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}