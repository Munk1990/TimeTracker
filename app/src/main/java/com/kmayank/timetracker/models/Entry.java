package com.kmayank.timetracker.models;

import java.util.Date;

/**
 * Created by kmayank on 11/27/16.
 */

public class Entry {
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    private String taskId;
    private Date entryDate;
    private int units;

    public Entry(String taskId, Date entryDate, int units) {
        this.taskId = taskId;
        this.entryDate = entryDate;
        this.units = units;
    }

}
