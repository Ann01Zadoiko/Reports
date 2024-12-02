package com.example.demo.tram;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/v1/trams")
public class TramController {

    public final TramService tramService;

    // show a list of trams
    @GetMapping("/")
    public String getList(Model model){
        model.addAttribute("trams", tramService.getAll());
        return "/trams/list";
    }

    //show a page for add a new tram
    @GetMapping("/add")
    public String add(@ModelAttribute("tram") Tram tram){
        return "/trams/add";
    }

    //add new user
    @PostMapping("/add")
    public String save(@ModelAttribute("tram") Tram tram){
        tramService.add(tram);
        return "redirect:/v1/trams/";
    }

    //show a page for edit
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id){
        model.addAttribute("tram", tramService.getById(id));
        return "/trams/edit";
    }

    //edit a tram by id
    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("tram") Tram tram){
        tramService.add(tram);
        return "redirect:/v1/trams/";
    }

    //delete a tram by id
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        tramService.deleteById(id);
        return "redirect:/v1/trams/";
    }
}
