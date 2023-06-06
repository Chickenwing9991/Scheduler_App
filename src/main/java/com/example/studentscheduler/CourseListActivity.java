package com.example.studentscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.studentscheduler.database.AppDatabase;
import com.example.studentscheduler.entity.Course;
import com.example.studentscheduler.util.CourseAdapter;

import java.util.List;

public class CourseListActivity extends AppCompatActivity {

    private AppDatabase db;
    private List<Course> courses;
    private RecyclerView recyclerView;
    private CourseAdapter courseAdapter;
    private Button addCourseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        db = AppDatabase.getInstance(getApplicationContext());
        recyclerView = findViewById(R.id.course_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadCourses();

        addCourseButton = findViewById(R.id.add_course_button);
        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseListActivity.this, CourseDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCourses();
    }

    private void loadCourses() {
        courses = db.courseDao().getAllCourses();
        courseAdapter = new CourseAdapter(courses, this);
        recyclerView.setAdapter(courseAdapter);
    }
}
