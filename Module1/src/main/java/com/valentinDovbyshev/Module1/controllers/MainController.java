package com.valentinDovbyshev.Module1.controllers;

import com.valentinDovbyshev.Module1.models.AddNewProject;
import com.valentinDovbyshev.Module1.models.AddNewTask;
import com.valentinDovbyshev.Module1.models.AddNewUser;
import com.valentinDovbyshev.Module1.repo.AddNewProjectRepository;
import com.valentinDovbyshev.Module1.repo.AddNewTaskRepository;
import com.valentinDovbyshev.Module1.repo.AddNewUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private AddNewProjectRepository addNewProjectRepository;

    @Autowired
    private AddNewTaskRepository addNewTaskRepository;

    @Autowired
    private AddNewUserRepository addNewUserRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Управління задачами проекту");
        Iterable<AddNewProject> projects = addNewProjectRepository.findAllByOrderByIdDesc();
        model.addAttribute("projects", projects);
        Iterable<AddNewTask> tasks = addNewTaskRepository.findAllByOrderByIdDesc();
        model.addAttribute("tasks", tasks);
        Iterable<AddNewUser> users = addNewUserRepository.findAllByOrderByIdDesc();
        model.addAttribute("users", users);
        return "home";
    }
}
