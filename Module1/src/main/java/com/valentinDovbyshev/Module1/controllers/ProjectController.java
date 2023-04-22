package com.valentinDovbyshev.Module1.controllers;

import com.valentinDovbyshev.Module1.models.AddNewProject;
import com.valentinDovbyshev.Module1.repo.AddNewProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ProjectController {

    @Autowired
    private AddNewProjectRepository addNewProjectRepository;

    @GetMapping("/main")
    public String newProjectMain(Model model) {
        Iterable<AddNewProject> projects = addNewProjectRepository.findAll();
        model.addAttribute("projects", projects);
        return "home";
    }

    @GetMapping("/newProject")
    public String newProject(Model model) {
        return "add-new-project";
    }

    @PostMapping("/newProject")
    public String newProjectAdd(@RequestParam String project_name, @RequestParam String project_description, @RequestParam String project_start_date, @RequestParam String project_end_date, Model model) {
        AddNewProject addNewProject = new AddNewProject(project_name, project_description, project_start_date, project_end_date);
        addNewProjectRepository.save(addNewProject);
        return "redirect:/newProject";
    }

    @GetMapping("/editProjectPage/{id}/edit")
    public String editProjectPage(@PathVariable(value = "id") long id, Model model) {
        Optional<AddNewProject> addNewProject = addNewProjectRepository.findById(id);
        ArrayList<AddNewProject> res = new ArrayList<>();
        addNewProject.ifPresent(res::add);
        model.addAttribute("addNewProject", res);
        return "edit-project";
    }

    @PostMapping("/editProjectPage/{id}/edit")
    public String ProjectPageUpdate(@PathVariable(value = "id") long id, @RequestParam String project_name, @RequestParam String project_description, @RequestParam String project_start_date, @RequestParam String project_end_date, Model model) {
        AddNewProject addNewProject = addNewProjectRepository.findById(id).orElseThrow();
        addNewProject.setProject_name(project_name);
        addNewProject.setDescription(project_description);
        addNewProject.setStart_date(project_start_date);
        addNewProject.setEnd_date(project_end_date);
        return "redirect:/home";
    }

    @PostMapping("/editProjectPage/{id}/remove")
    public String ProjectPageDelete(@PathVariable(value = "id") long id, Model model) {
        AddNewProject addNewProject = addNewProjectRepository.findById(id).orElseThrow();
        addNewProjectRepository.delete(addNewProject);
        return "redirect:/home";
    }
}
