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

    //list
    @GetMapping("/")
    public String getList(Model model){
        model.addAttribute("trams", tramService.getAll());
        return "/trams/list";
    }

    //add
    @GetMapping("/add")
    public String add(@ModelAttribute("tram") Tram tram){
        return "/trams/add";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("tram") Tram tram){
        tramService.add(tram);
        return "redirect:/v1/trams/";
    }

    //edit
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id){
        model.addAttribute("tram", tramService.getById(id));
        return "/trams/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("tram") Tram tram){
        tramService.add(tram);
        return "redirect:/v1/trams/";
    }

    //delete
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        tramService.deleteById(id);
        return "redirect:/v1/trams/";
    }
}
