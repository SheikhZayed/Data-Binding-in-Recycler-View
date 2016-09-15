package com.example.techjini.recyclerbasics;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.techjini.recyclerbasics.adapters.CustomAdapter;
import com.example.techjini.recyclerbasics.databinding.ActivityMainBinding;
import com.example.techjini.recyclerbasics.databinding.RecyclerItemsBinding;
import com.example.techjini.recyclerbasics.helpers.DBOpenHelper;
import com.example.techjini.recyclerbasics.model.Bookmark;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager mLayoutManager;
    ActivityMainBinding binding;
    private CustomAdapter mAdapter;
    List<Bookmark> bookmarkList = new ArrayList<>();
    DBOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mLayoutManager = new LinearLayoutManager(this);
        db = new DBOpenHelper(this);
        binding.recyclerview.setLayoutManager(mLayoutManager);
        initViews();
    }

    private void initViews() {
//        db.addBookmark();
        bookmarkList = db.getAllBookmark();
        mAdapter = new CustomAdapter(bookmarkList, this, db);
        binding.recyclerview.setAdapter(mAdapter);
    }
}