package com.example.studentscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.studentscheduler.database.AppDatabase;
import com.example.studentscheduler.entity.Course;
import com.example.studentscheduler.util.NotificationHelper;
import com.example.studentscheduler.viewmodel.CourseViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CourseDetailActivity extends AppCompatActivity {

    private CourseViewModel courseViewModel;
    private EditText courseTitleEditText;
    private TextView startDateTextView;
    private TextView endDateTextView;
    private Spinner statusSpinner;
    private EditText instructorNameEditText;
    private EditText instructorPhoneEditText;
    private EditText instructorEmailEditText;
    private Button saveButton;
    private Button deleteButton;
    private Button shareButton;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        courseViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(CourseViewModel.class);

        courseId = getIntent().getIntExtra("courseId", -1);

        courseTitleEditText = findViewById(R.id.courseTitleEditText);
        startDateTextView = findViewById(R.id.startDateTextView);
        endDateTextView = findViewById(R.id.endDateTextView);
        statusSpinner = findViewById(R.id.statusSpinner);
        instructorNameEditText = findViewById(R.id.instructorNameEditText);
        instructorPhoneEditText = findViewById(R.id.instructorPhoneEditText);
        instructorEmailEditText = findViewById(R.id.instructorEmailEditText);
        saveButton = findViewById(R.id.saveButton);
        deleteButton = findViewById(R.id.deleteButton);
        shareButton = findViewById(R.id.shareButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.course_status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

        if (courseId != -1) {
            Course course = courseViewModel.getCourseById(courseId);
            courseTitleEditText.setText(course.getTitle());
            startDateTextView.setText(course.getStartDate());
            endDateTextView.setText(course.getEndDate());
            statusSpinner.setSelection(adapter.getPosition(course.getStatus()));
            instructorNameEditText.setText(course.getInstructorName());
            instructorPhoneEditText.setText(course.getInstructorPhone());
            instructorEmailEditText.setText(course.getInstructorEmail());
        }

        startDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CourseDetailActivity.this, startDateListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        endDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CourseDetailActivity.this, endDateListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCourse();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCourse();
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareNotes();
            }
        });
    }

    private void saveCourse() {
        Course course = new Course(courseTitleEditText.getText().toString(), startDateTextView.getText().toString(), endDateTextView.getText().toString(), statusSpinner.getSelectedItem().toString(), instructorNameEditText.getText().toString(), instructorPhoneEditText.getText().toString(), instructorEmailEditText.getText().toString());
        if (courseId == -1) {
            courseViewModel.insertCourse(course);
        } else {
            course.setId(courseId);
            courseViewModel.updateCourse(course);
        }
        finish();
    }

    private void deleteCourse() {
        if (courseId != -1) {
            courseViewModel.deleteCourse(courseId);
        }
        finish();
    }

    private void shareNotes() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Course Notes");
        intent.putExtra(Intent.EXTRA_TEXT, "Course: " + courseTitleEditText.getText().toString() + "\nInstructor: " + instructorNameEditText.getText().toString() + "\nPhone: " + instructorPhoneEditText.getText().toString() + "\nEmail: " + instructorEmailEditText.getText().toString());
        startActivity(Intent.createChooser(intent, "Share via"));
    }

    DatePickerDialog.OnDateSetListener startDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            startDateTextView.setText(dateFormat.format(calendar.getTime()));
        }
    };

    DatePickerDialog.OnDateSetListener endDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            endDateTextView.setText(dateFormat.format(calendar.getTime()));
        }
    };
}
