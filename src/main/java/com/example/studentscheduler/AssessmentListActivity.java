package com.example.studentscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.studentscheduler.database.AppDatabase;
import com.example.studentscheduler.entity.Assessment;

import java.util.List;

public class AssessmentListActivity extends AppCompatActivity {

    private AppDatabase db;
    private RecyclerView recyclerView;
    private AssessmentAdapter assessmentAdapter;
    private Button addAssessmentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);

        db = AppDatabase.getInstance(getApplicationContext());
        recyclerView = findViewById(R.id.assessment_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadAssessmentList();

        addAssessmentButton = findViewById(R.id.add_assessment_button);
        addAssessmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssessmentListActivity.this, AssessmentDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAssessmentList();
    }

    private void loadAssessmentList() {
        List<Assessment> assessments = db.assessmentDao().getAllAssessments();
        assessmentAdapter = new AssessmentAdapter(this, assessments);
        recyclerView.setAdapter(assessmentAdapter);
    }
}
