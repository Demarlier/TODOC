package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.Database.Dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectDataRepository {

    private final ProjectDao mProjectDao;

    public ProjectDataRepository(ProjectDao mProjectDao) { this.mProjectDao = mProjectDao; }

    // --- GET Project ---
    public LiveData<Project> getProjectById(long id) { return this.mProjectDao.getProjectById(id); }

    public LiveData<List<Project>> getAllProjects(){return this.mProjectDao.getAllProjects();}
}

