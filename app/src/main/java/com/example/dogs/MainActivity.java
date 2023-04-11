package com.example.dogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    private ImageView imageView;
    private ProgressBar progressBar;
    private Button button_next_image;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.loadDogImage();
        viewModel.getIsError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isError) {
                if(isError){
                    Toast.makeText(
                            MainActivity.this,
                            R.string.error_messege,
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
        viewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loading) {
                if (loading){
                    progressBar.setVisibility(View.VISIBLE);
                }else {
                    progressBar.setVisibility(View.GONE);
                }

            }
        });
        viewModel.getDogsImage().observe(this, new Observer<DogsImage>() {
            @Override
            public void onChanged(DogsImage dogsImage) {
                Glide.with(MainActivity.this)
                        .load(dogsImage.getMessage())
                        .into(imageView);
            }
        });
        button_next_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.loadDogImage();
            }
        });

    }

    private void initViews() {
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);
        button_next_image = findViewById(R.id.button_next_image);
    }

}

