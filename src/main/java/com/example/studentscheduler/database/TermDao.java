package com.example.studentscheduler.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentscheduler.entity.Term;

import java.util.List;

@Dao
public interface TermDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTerm(Term term);

    @Update
    void updateTerm(Term term);

    @Delete
    void deleteTerm(Term term);

    @Query("SELECT * FROM term WHERE termId = :id")
    Term getTermById(int id);

    @Query("SELECT * FROM term ORDER BY startDate ASC")
    LiveData<List<Term>> getAllTerms();

    @Query("SELECT COUNT(*) FROM term WHERE termId = :termId")
    int getCourseCountForTerm(int termId);
}
