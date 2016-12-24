package com.kmayank.timetracker.dao;

import com.kmayank.timetracker.models.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kmayank on 11/29/16.
 */

public interface ITaskDao {

    public void saveTask(Task task);
    public void deleteTask(Task task);
    public Task findTask(String taskId);
    public List<Task> listAllTasks();
    public List<Task> listAllSubTasks(Task task);
}
