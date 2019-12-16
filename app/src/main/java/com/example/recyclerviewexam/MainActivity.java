package com.example.recyclerviewexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyRecyclerAdapter.MyRecyclerVIewClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //가상 데이터 생성부분
        List<CardItem> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataList.add(new CardItem(i + "번째","설명"));
        }

        MyRecyclerAdapter adapter = new MyRecyclerAdapter(dataList);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListner(this);

    }

    @Override
    public void onItemClicked(int position) {
        Toast.makeText(this,""+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShareButtonClicked(int position) {
        Toast.makeText(this,"Share "+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLearnMoreButtonClicked(int position) {
        Toast.makeText(this,"More "+position,Toast.LENGTH_SHORT).show();
    }
}
