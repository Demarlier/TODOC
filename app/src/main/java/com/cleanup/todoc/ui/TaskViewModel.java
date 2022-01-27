package com.cleanup.todoc.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.Observer;
import java.util.concurrent.Executor;
import java.util.function.Function;

public class  TaskViewModel extends ViewModel {
    // REPOSITORIES
    private final ProjectDataRepository mProjectDataRepository;
    private final TaskDataRepository mTaskDataRepository;
    private final Executor executor;

    // DATA
    @Nullable
    private LiveData<Project> currentProject;
    private List<Project> mProjects;

    public TaskViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {
        this.mTaskDataRepository = taskDataSource;
        this.mProjectDataRepository = projectDataSource;
        this.executor = executor;
    }

    public void init(long id) {
        if (this.currentProject != null) {
            return;
        }

        currentProject = mProjectDataRepository.getProjectById(id);
    }


    // -------------
    // FOR TASK
    // -------------


    public void createTask(Task task) {
        executor.execute(() -> {
            mTaskDataRepository.createTask(task);
        });
    }

    public void deleteTask(long id) {
        executor.execute(() -> {
            mTaskDataRepository.deleteTask(id);
        });
    }

    public void updateTask(Task task) {
        executor.execute(() -> {
            mTaskDataRepository.updateTask(task);
        });
    }

    public void setObserverForGetAllProjects(@NonNull LifecycleOwner owner) {
        mProjectDataRepository.getAllProjects().observe(owner,(projectList)-> mProjects = projectList);
    }

    public List<Project> getProjects(){
        return mProjects;
    }

    public void setObserverForGetAllTasks(@NonNull LifecycleOwner owner, android.arch.lifecycle.Observer<List<Task>> updateTasks) {
        mTaskDataRepository.getAllTasks().observe(owner,updateTasks);
    }
}
