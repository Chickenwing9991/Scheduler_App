package com.example.studentscheduler.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentscheduler.entity.Course;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCourse(Course course);

    @Update
    void updateCourse(Course course);

    @Delete
    void deleteCourse(Course course);

    @Query("SELECT * FROM course WHERE termId = :termId ORDER BY startDate")
    LiveData<List<Course>> getCoursesByTerm(int termId);

    @Query("SELECT * FROM course WHERE courseId = :courseId")
    LiveData<Course> getCourseById(int courseId);

    @Query("DELETE FROM course")
    void deleteAllCourses();
}
