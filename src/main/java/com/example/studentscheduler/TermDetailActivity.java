package com.example.studentscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.studentscheduler.entity.Term;
import com.example.studentscheduler.viewmodel.TermViewModel;

public class TermDetailActivity extends AppCompatActivity {

    private TermViewModel termViewModel;
    private EditText termTitleEditText;
    private TextView startDateTextView;
    private TextView endDateTextView;
    private Button saveButton;
    private Button cancelButton;
    private Term currentTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);

        termViewModel = new ViewModelProvider(this).get(TermViewModel.class);

        termTitleEditText = findViewById(R.id.termTitleEditText);
        startDateTextView = findViewById(R.id.startDateTextView);
        endDateTextView = findViewById(R.id.endDateTextView);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);

        Intent intent = getIntent();
        if (intent.hasExtra("term")) {
            currentTerm = (Term) intent.getSerializableExtra("term");
            termTitleEditText.setText(currentTerm.getTitle());
            startDateTextView.setText(currentTerm.getStartDate());
            endDateTextView.setText(currentTerm.getEndDate());
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTerm();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void saveTerm() {
        String title = termTitleEditText.getText().toString();
        String startDate = startDateTextView.getText().toString();
        String endDate = endDateTextView.getText().toString();

        if (currentTerm == null) {
            Term newTerm = new Term(title, startDate, endDate);
            termViewModel.insert(newTerm);
        } else {
            currentTerm.setTitle(title);
            currentTerm.setStartDate(startDate);
            currentTerm.setEndDate(endDate);
            termViewModel.update(currentTerm);
        }

        finish();
    }
}
