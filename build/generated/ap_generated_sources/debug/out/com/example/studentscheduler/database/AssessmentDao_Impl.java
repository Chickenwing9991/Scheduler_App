package com.example.studentscheduler.database;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.studentscheduler.entity.Assessment;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AssessmentDao_Impl implements AssessmentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Assessment> __insertionAdapterOfAssessment;

  private final EntityDeletionOrUpdateAdapter<Assessment> __deletionAdapterOfAssessment;

  private final EntityDeletionOrUpdateAdapter<Assessment> __updateAdapterOfAssessment;

  public AssessmentDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAssessment = new EntityInsertionAdapter<Assessment>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `assessment` (`id`,`title`,`type`,`end_date`,`course_id`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Assessment value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getType() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getType());
        }
        stmt.bindLong(5, value.getCourseId());
      }
    };
    this.__deletionAdapterOfAssessment = new EntityDeletionOrUpdateAdapter<Assessment>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `assessment` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Assessment value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfAssessment = new EntityDeletionOrUpdateAdapter<Assessment>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `assessment` SET `id` = ?,`title` = ?,`type` = ?,`end_date` = ?,`course_id` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Assessment value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getType() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getType());
        }
        stmt.bindLong(5, value.getCourseId());
        stmt.bindLong(6, value.getId());
      }
    };
  }

  @Override
  public void insertAssessment(final Assessment assessment) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAssessment.insert(assessment);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAssessment(final Assessment assessment) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfAssessment.handle(assessment);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateAssessment(final Assessment assessment) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfAssessment.handle(assessment);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Assessment> getAssessmentsByCourse(final int courseId) {
    final String _sql = "SELECT * FROM assessment WHERE courseId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, courseId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Assessment getAssessmentById(final int assessmentId) {
    final String _sql = "SELECT * FROM assessment WHERE assessmentId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, assessmentId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
