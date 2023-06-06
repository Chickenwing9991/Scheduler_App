package com.example.studentscheduler.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "assessment",
        foreignKeys = @ForeignKey(entity = Course.class,
                                  parentColumns = "id",
                                  childColumns = "course_id",
                                  onDelete = ForeignKey.CASCADE))
public class Assessment {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "end_date")
    private Date endDate;

    @ColumnInfo(name = "course_id")
    private int courseId;

    public Assessment(String title, String type, Date endDate, int courseId) {
        this.title = title;
        this.type = type;
        this.endDate = endDate;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
