package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.Database.Dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository {

    private final TaskDao mTaskDao;

    public TaskDataRepository(TaskDao mTaskDao) { this.mTaskDao = mTaskDao; }

    // --- GET ---

    public LiveData<List<Task>> getAllTasks(){ return this.mTaskDao.getTasks(); }

    // --- CREATE ---

    public void createTask(Task task){ mTaskDao.insertTask(task); }

    // --- DELETE ---
    public void deleteTask(long taskId){ mTaskDao.deleteTask(taskId); }

    // --- UPDATE ---
    public void updateTask(Task task){ mTaskDao.updateTask(task); }
}
