package com.example.demo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    //list
    @GetMapping("/")
    public String showAllUsers(Model model){
        model.addAttribute("users", userService.getAll());
        return "/users/list";
    }

    //update
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id){
        model.addAttribute("user", userService.getById(id));
        return "/users/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("user") User user){
        userService.update(user);
        return "redirect:/v1/users/";
    }

    //create
    @GetMapping("/add")
    public String add(@ModelAttribute("user") User user){
        return "/users/add";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("user") User user){
        userService.add(user);
        return "redirect:/v1/users/";
    }

    //delete
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/v1/users/";
    }
}
