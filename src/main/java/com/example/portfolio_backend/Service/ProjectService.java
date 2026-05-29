package com.example.portfolio_backend.Service;

import com.example.portfolio_backend.Entity.Project;
import com.example.portfolio_backend.Repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository=projectRepository;
    }

    public List<Project> getAllProjects(){
            return projectRepository.findAll();
    }
public Project addProject(Project project){
        return projectRepository.save(project);
}

}
