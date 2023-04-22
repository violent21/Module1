package com.valentinDovbyshev.Module1.controllers;

import com.valentinDovbyshev.Module1.models.AddNewProject;
import com.valentinDovbyshev.Module1.models.AddNewUser;
import com.valentinDovbyshev.Module1.repo.AddNewUserRepository;
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
public class UserController {

    @Autowired
    private AddNewUserRepository addNewUserRepository;

    @GetMapping("/mainUser")
    public String newUserMain(Model model) {
        Iterable<AddNewUser> users = addNewUserRepository.findAll();
        model.addAttribute("users", users);
        return "home";
    }

    @GetMapping("/newUser")
    public String newUser(Model model) {
        return "add-new-user";
    }

    @PostMapping("/newUser")
    public String newUserAdd(@RequestParam String user_name, @RequestParam String user_email, @RequestParam String user_password, Model model) {
        AddNewUser addNewUser = new AddNewUser(user_name, user_email, user_password);
        addNewUserRepository.save(addNewUser);
        return "redirect:/newUser";
    }

    @GetMapping("/editUserPage/{id}/edit")
    public String editUserPage(@PathVariable(value = "id") long id, Model model) {
        Optional<AddNewUser> addNewUser = addNewUserRepository.findById(id);
        ArrayList<AddNewUser> res = new ArrayList<>();
        addNewUser.ifPresent(res::add);
        model.addAttribute("addNewUser", res);
        return "edit-user";
    }

    @PostMapping("/editUserPage/{id}/edit")
    public String UserPageUpdate(@PathVariable(value = "id") long id, @RequestParam String user_name, @RequestParam String user_email, @RequestParam String user_password, Model model) {
        AddNewUser addNewUser = addNewUserRepository.findById(id).orElseThrow();
        addNewUser.setUser_name(user_name);
        addNewUser.setUser_email(user_email);
        addNewUser.setUser_password(user_password);
        return "redirect:/home";
    }

    @PostMapping("/editUserPage/{id}/remove")
    public String UserPageDelete(@PathVariable(value = "id") long id, Model model) {
        AddNewUser addNewUser = addNewUserRepository.findById(id).orElseThrow();
        addNewUserRepository.delete(addNewUser);
        return "redirect:/home";
    }
}
