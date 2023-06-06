package com.example.studentscheduler.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentscheduler.entity.Assessment;

import java.util.List;

@Dao
public interface AssessmentDao {

    @Insert
    void insertAssessment(Assessment assessment);

    @Update
    void updateAssessment(Assessment assessment);

    @Delete
    void deleteAssessment(Assessment assessment);

    @Query("SELECT * FROM assessment WHERE courseId = :courseId")
    List<Assessment> getAssessmentsByCourse(int courseId);

    @Query("SELECT * FROM assessment WHERE assessmentId = :assessmentId")
    Assessment getAssessmentById(int assessmentId);
}
