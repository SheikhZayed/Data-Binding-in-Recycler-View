package com.example.techjini.recyclerbasics;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.example.techjini.recyclerbasics.databinding.ActivityWebviewBinding;

public class WebviewActivity extends AppCompatActivity {

    ActivityWebviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_webview);
        binding.webview.setWebViewClient(new WebViewClient());

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String url = bundle.getString("LINK");
            binding.webview.loadUrl("http://"+url);
        }
    }
}
