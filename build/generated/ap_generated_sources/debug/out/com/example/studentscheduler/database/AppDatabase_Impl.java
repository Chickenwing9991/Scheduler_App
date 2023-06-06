package com.example.studentscheduler.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile TermDao _termDao;

  private volatile CourseDao _courseDao;

  private volatile AssessmentDao _assessmentDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `term` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT, `start_date` INTEGER, `end_date` INTEGER)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `courses` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `term_id` INTEGER NOT NULL, `course_title` TEXT, `start_date` TEXT, `end_date` TEXT, `status` TEXT, `instructor_name` TEXT, `instructor_phone` TEXT, `instructor_email` TEXT, FOREIGN KEY(`term_id`) REFERENCES `term`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `assessment` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT, `type` TEXT, `end_date` TEXT, `course_id` INTEGER NOT NULL, FOREIGN KEY(`course_id`) REFERENCES `courses`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c9d17e57a34f7715c978a03f191fa44a')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `term`");
        _db.execSQL("DROP TABLE IF EXISTS `courses`");
        _db.execSQL("DROP TABLE IF EXISTS `assessment`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsTerm = new HashMap<String, TableInfo.Column>(4);
        _columnsTerm.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTerm.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTerm.put("start_date", new TableInfo.Column("start_date", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTerm.put("end_date", new TableInfo.Column("end_date", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTerm = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTerm = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTerm = new TableInfo("term", _columnsTerm, _foreignKeysTerm, _indicesTerm);
        final TableInfo _existingTerm = TableInfo.read(_db, "term");
        if (! _infoTerm.equals(_existingTerm)) {
          return new RoomOpenHelper.ValidationResult(false, "term(com.example.studentscheduler.entity.Term).\n"
                  + " Expected:\n" + _infoTerm + "\n"
                  + " Found:\n" + _existingTerm);
        }
        final HashMap<String, TableInfo.Column> _columnsCourses = new HashMap<String, TableInfo.Column>(9);
        _columnsCourses.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("term_id", new TableInfo.Column("term_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("course_title", new TableInfo.Column("course_title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("start_date", new TableInfo.Column("start_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("end_date", new TableInfo.Column("end_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("instructor_name", new TableInfo.Column("instructor_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("instructor_phone", new TableInfo.Column("instructor_phone", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("instructor_email", new TableInfo.Column("instructor_email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCourses = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysCourses.add(new TableInfo.ForeignKey("term", "CASCADE", "NO ACTION",Arrays.asList("term_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesCourses = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCourses = new TableInfo("courses", _columnsCourses, _foreignKeysCourses, _indicesCourses);
        final TableInfo _existingCourses = TableInfo.read(_db, "courses");
        if (! _infoCourses.equals(_existingCourses)) {
          return new RoomOpenHelper.ValidationResult(false, "courses(com.example.studentscheduler.entity.Course).\n"
                  + " Expected:\n" + _infoCourses + "\n"
                  + " Found:\n" + _existingCourses);
        }
        final HashMap<String, TableInfo.Column> _columnsAssessment = new HashMap<String, TableInfo.Column>(5);
        _columnsAssessment.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAssessment.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAssessment.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAssessment.put("end_date", new TableInfo.Column("end_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAssessment.put("course_id", new TableInfo.Column("course_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAssessment = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysAssessment.add(new TableInfo.ForeignKey("courses", "CASCADE", "NO ACTION",Arrays.asList("course_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesAssessment = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAssessment = new TableInfo("assessment", _columnsAssessment, _foreignKeysAssessment, _indicesAssessment);
        final TableInfo _existingAssessment = TableInfo.read(_db, "assessment");
        if (! _infoAssessment.equals(_existingAssessment)) {
          return new RoomOpenHelper.ValidationResult(false, "assessment(com.example.studentscheduler.entity.Assessment).\n"
                  + " Expected:\n" + _infoAssessment + "\n"
                  + " Found:\n" + _existingAssessment);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "c9d17e57a34f7715c978a03f191fa44a", "ecdf922341b6a6b2a341920b3fa5671f");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "term","courses","assessment");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `term`");
      _db.execSQL("DELETE FROM `courses`");
      _db.execSQL("DELETE FROM `assessment`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(TermDao.class, TermDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CourseDao.class, CourseDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AssessmentDao.class, AssessmentDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public TermDao termDao() {
    if (_termDao != null) {
      return _termDao;
    } else {
      synchronized(this) {
        if(_termDao == null) {
          _termDao = new TermDao_Impl(this);
        }
        return _termDao;
      }
    }
  }

  @Override
  public CourseDao courseDao() {
    if (_courseDao != null) {
      return _courseDao;
    } else {
      synchronized(this) {
        if(_courseDao == null) {
          _courseDao = new CourseDao_Impl(this);
        }
        return _courseDao;
      }
    }
  }

  @Override
  public AssessmentDao assessmentDao() {
    if (_assessmentDao != null) {
      return _assessmentDao;
    } else {
      synchronized(this) {
        if(_assessmentDao == null) {
          _assessmentDao = new AssessmentDao_Impl(this);
        }
        return _assessmentDao;
      }
    }
  }
}
