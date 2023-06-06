package com.example.studentscheduler.database;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.studentscheduler.entity.Term;
import com.example.studentscheduler.util.Converters;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TermDao_Impl implements TermDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Term> __insertionAdapterOfTerm;

  private final EntityDeletionOrUpdateAdapter<Term> __deletionAdapterOfTerm;

  private final EntityDeletionOrUpdateAdapter<Term> __updateAdapterOfTerm;

  public TermDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTerm = new EntityInsertionAdapter<Term>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `term` (`id`,`title`,`start_date`,`end_date`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Term value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        final Long _tmp;
        _tmp = Converters.dateToTimestamp(value.getStartDate());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, _tmp);
        }
        final Long _tmp_1;
        _tmp_1 = Converters.dateToTimestamp(value.getEndDate());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp_1);
        }
      }
    };
    this.__deletionAdapterOfTerm = new EntityDeletionOrUpdateAdapter<Term>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `term` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Term value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfTerm = new EntityDeletionOrUpdateAdapter<Term>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `term` SET `id` = ?,`title` = ?,`start_date` = ?,`end_date` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Term value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        final Long _tmp;
        _tmp = Converters.dateToTimestamp(value.getStartDate());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, _tmp);
        }
        final Long _tmp_1;
        _tmp_1 = Converters.dateToTimestamp(value.getEndDate());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp_1);
        }
        stmt.bindLong(5, value.getId());
      }
    };
  }

  @Override
  public void insertTerm(final Term term) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTerm.insert(term);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteTerm(final Term term) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfTerm.handle(term);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateTerm(final Term term) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTerm.handle(term);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Term getTermById(final int id) {
    final String _sql = "SELECT * FROM term WHERE termId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
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
  public LiveData<List<Term>> getAllTerms() {
    final String _sql = "SELECT * FROM term ORDER BY startDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"term"}, false, new Callable<List<Term>>() {
      @Override
      public List<Term> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public int getCourseCountForTerm(final int termId) {
    final String _sql = "SELECT COUNT(*) FROM term WHERE termId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, termId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
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
