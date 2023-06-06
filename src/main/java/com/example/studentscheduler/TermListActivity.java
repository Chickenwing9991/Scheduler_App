package com.example.studentscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.studentscheduler.database.AppDatabase;
import com.example.studentscheduler.entity.Term;
import com.example.studentscheduler.util.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class TermListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Term> termList;
    private AppDatabase db;
    private Button addTermButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);

        db = AppDatabase.getInstance(getApplicationContext());
        termList = new ArrayList<>();

        recyclerView = findViewById(R.id.termRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addTermButton = findViewById(R.id.addTermButton);
        addTermButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TermListActivity.this, TermDetailActivity.class);
                startActivity(intent);
            }
        });

        loadTermData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTermData();
    }

    private void loadTermData() {
        termList = db.termDao().getAllTerms();
        recyclerViewAdapter = new RecyclerViewAdapter(termList, this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
