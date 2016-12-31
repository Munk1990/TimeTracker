package com.kmayank.timetracker.models;

import java.util.Date;

/**
 * Created by kmayank on 11/27/16.
 */

public class Task {
    private String parentTaskId;
    private String taskId;
    private String taskName;
    private boolean taskActive = true;

    public int getCounterMinutes() {
        return counterMinutes;
    }

    public void setCounterMinutes(int counterMinutes) {
        this.counterMinutes = counterMinutes;
    }

    private int counterMinutes;

    public Integer getAlarmMinutes() {
        return alarmMinutes;
    }

    public void setAlarmMinutes(Integer alarmMinutes) {
        this.alarmMinutes = alarmMinutes;
    }

    private Integer alarmMinutes;
    private Date lastAccessed;

    public String getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isTaskActive() {
        return taskActive;
    }

    public String getParentTaskId() {
        return parentTaskId;
    }


    public Task(String taskname){
        this.taskName = taskname;
        this.taskId = Long.toString(new Date().getTime());
    }



    public void setName(String name) {
       this.taskName = name;
    }

    public void setParent(String parentTask) {
       this.parentTaskId = parentTaskId;
    }

    public void setTaskComplete(boolean taskComplete) {
        taskActive = !taskComplete;
    }

    public Task duplicateTask(boolean saveProgress) {
        Task newTask = new Task(this.taskName);
        return newTask;
    }

    public void enableTimer(int hours, int minutes){
        if (hours >= 0 && hours < 24 && minutes >= 0 && minutes < 60 )
        this.alarmMinutes = hours*60+minutes;
    }
}
