package com.app.EmployeManagement.Service;

import com.app.EmployeManagement.Entity.Project;
import com.app.EmployeManagement.Repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getProjects(){
        return projectRepository.findAll();
    }
}
