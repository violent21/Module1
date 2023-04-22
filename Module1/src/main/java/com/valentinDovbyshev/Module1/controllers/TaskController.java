package com.valentinDovbyshev.Module1.controllers;

import com.valentinDovbyshev.Module1.models.AddNewProject;
import com.valentinDovbyshev.Module1.models.AddNewTask;
import com.valentinDovbyshev.Module1.repo.AddNewTaskRepository;
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
public class TaskController {

    @Autowired
    private AddNewTaskRepository addNewTaskRepository;

    @GetMapping("/mainTask")
    public String newTaskMain(Model model) {
        Iterable<AddNewTask> tasks = addNewTaskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "home";
    }

    @GetMapping("/newTask")
    public String newTask(Model model) {
        return "add-new-task";
    }

    @PostMapping("/newTask")
    public String newTaskAdd(@RequestParam String task_name, @RequestParam String description, @RequestParam String start_date, @RequestParam String end_date, @RequestParam String responsible_person, Model model) {
        AddNewTask addNewTask = new AddNewTask(task_name, description, start_date, end_date, responsible_person);
        addNewTaskRepository.save(addNewTask);
        return "redirect:/newTask";
    }

    @GetMapping("/editTaskPage/{id}/edit")
    public String editTaskPage(@PathVariable(value = "id") long id, Model model) {
        Optional<AddNewTask> addNewTask = addNewTaskRepository.findById(id);
        ArrayList<AddNewTask> res = new ArrayList<>();
        addNewTask.ifPresent(res::add);
        model.addAttribute("addNewTask", res);
        return "edit-task";
    }

    @PostMapping("/editTaskPage/{id}/edit")
    public String TaskPageUpdate(@PathVariable(value = "id") long id, @RequestParam String task_name, @RequestParam String description, @RequestParam String start_date, @RequestParam String end_date, @RequestParam String responsible_person, Model model) {
        AddNewTask addNewTask = addNewTaskRepository.findById(id).orElseThrow();
        addNewTask.setTask_name(task_name);
        addNewTask.setDescription(description);
        addNewTask.setStart_date(start_date);
        addNewTask.setEnd_date(end_date);
        return "redirect:/home";
    }

    @PostMapping("/editTaskPage/{id}/remove")
    public String TaskPageDelete(@PathVariable(value = "id") long id, Model model) {
        AddNewTask addNewTask = addNewTaskRepository.findById(id).orElseThrow();
        addNewTaskRepository.delete(addNewTask);
        return "redirect:/home";
    }
}
