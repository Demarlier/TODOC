package com.cleanup.todoc.Injections;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Executor;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final TaskDataRepository mTaskDataSource;
    private final ProjectDataRepository mProjectDataSource;
    private final Executor mExecutor;

    public ViewModelFactory(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {
        mTaskDataSource = taskDataSource;
        mProjectDataSource = projectDataSource;
        mExecutor = executor;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        try {
            return
                    modelClass.getConstructor(TaskDataRepository.class, ProjectDataRepository.class, Executor.class).newInstance(mTaskDataSource, mProjectDataSource, mExecutor);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
