package com.example.portfolio_backend.Controller;

import com.example.portfolio_backend.Entity.Project;
import com.example.portfolio_backend.Service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins="http://localhost:5173")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService=projectService;
    }
    @GetMapping
    public List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }

    @PostMapping
    public Project addProject(@Valid @RequestBody Project project){
        return projectService.addProject(project);
    }
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
    }
    @PutMapping("/{id}")
    public Project updateProject(
            @PathVariable Long id,
            @Valid @RequestBody Project project) {
        return projectService.updateProject(id, project);
    }
}
