package com.example.studentscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
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

import com.example.studentscheduler.entity.Assessment;
import com.example.studentscheduler.util.NotificationHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AssessmentDetailActivity extends AppCompatActivity {

    private Assessment assessmentViewModel;
    private EditText assessmentTitle;
    private TextView assessmentDueDate;
    private Spinner assessmentType;
    private Button saveButton;
    private Button deleteButton;
    private Button setAlertButton;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private int assessmentId;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail);

        assessmentViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(AssessmentViewModel.class);

        assessmentTitle = findViewById(R.id.assessment_detail_title_label);
        assessmentDueDate = findViewById(R.id.assessment_detail_end_date);
        assessmentType = findViewById(R.id.assessment_detail_type);
        saveButton = findViewById(R.id.assessment_detail_save_button);
        deleteButton = findViewById(R.id.assessment_detail_delete_button);
        setAlertButton = findViewById(R.id.setAlertButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.assessment_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assessmentType.setAdapter(adapter);

        dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        calendar = Calendar.getInstance();

        Intent intent = getIntent();
        assessmentId = intent.getIntExtra("assessmentId", -1);

        if (assessmentId != -1) {
            assessmentViewModel.getAssessmentById(assessmentId).observe(this, assessment -> {
                if (assessment != null) {
                    assessmentTitle.setText(assessment.getTitle());
                    assessmentDueDate.setText(assessment.getDueDate());
                    assessmentType.setSelection(adapter.getPosition(assessment.getType()));
                }
            });
        }

        assessmentDueDate.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(AssessmentDetailActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        saveButton.setOnClickListener(view -> {
            saveAssessment();
            finish();
        });

        deleteButton.setOnClickListener(view -> {
            deleteAssessment();
            finish();
        });

        setAlertButton.setOnClickListener(view -> {
            NotificationHelper.createNotificationChannel(getApplicationContext());
        });
    }

    private void saveAssessment() {
        String title = assessmentTitle.getText().toString();
        String dueDate = assessmentDueDate.getText().toString();
        String type = assessmentType.getSelectedItem().toString();

        if (assessmentId == -1) {
            Assessment newAssessment = new Assessment(title, dueDate, type);
            assessmentViewModel.insert(newAssessment);
        } else {
            Assessment updatedAssessment = new Assessment(assessmentId, title, dueDate, type);
            assessmentViewModel.update(updatedAssessment);
        }
    }

    private void deleteAssessment() {
        if (assessmentId != -1) {
            assessmentViewModel.deleteById(assessmentId);
        }
    }

    private final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            assessmentDueDate.setText(dateFormat.format(calendar.getTime()));
        }
    };
}
