package com.example.portfolio_backend.Service;

import com.example.portfolio_backend.Entity.Project;
import com.example.portfolio_backend.Repository.ProjectRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> getFeaturedProjects() {
        return projectRepository.findByFeaturedTrue();
    }

    public Project addProject(Project project) {
        return projectRepository.save(project);
    }


    public void deleteProject(Long id) {

        projectRepository.deleteById(id);
    }

    public Project updateProject(Long id, Project updatedProject) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        existingProject.setTitle(updatedProject.getTitle());
        existingProject.setDescription(updatedProject.getDescription());
        existingProject.setTechStack(updatedProject.getTechStack());
        existingProject.setCategory(updatedProject.getCategory());
        existingProject.setFeatured(updatedProject.isFeatured());

        return projectRepository.save(existingProject);
    }
}