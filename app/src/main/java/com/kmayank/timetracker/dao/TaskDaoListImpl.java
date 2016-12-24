package com.kmayank.timetracker.dao;

import com.kmayank.timetracker.models.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kmayank on 11/27/16.
 */

public class TaskDaoListImpl implements ITaskDao {

    List<Task> taskList = new ArrayList<>();
    @Override
    public void saveTask(Task task) {
        taskList.add(task);
    }

    @Override
    public void deleteTask(Task task) {
        taskList.remove(task);
    }

    @Override
    public Task findTask(String taskId) {
        for (Task t: taskList){
            if (t.getTaskId() == taskId){
                return t;
            }
        }
        return null;
    }

    @Override
    public List<Task> listAllTasks() {
        return taskList;
    }

    @Override
    public List<Task> listAllSubTasks(Task task) {
        List<Task> subTaskList = new ArrayList<Task>();
        for (Task t: taskList){
            if (t.getParentTaskId() == task.getTaskId()){
               taskList.add(t);
            }
        }
        return subTaskList;
    }
}
