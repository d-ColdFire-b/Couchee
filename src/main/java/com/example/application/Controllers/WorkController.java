package com.example.application.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class WorkController {

    @GetMapping("/work")
    public String work (Model model){

        return "work";
    }

    @PostMapping("/addprop")
    public String addprop (Model model){


        return "adddrop";
    }
    @PostMapping("/addtype")
    public String addtype(Model model){

        return ("addtype");
    }
}
