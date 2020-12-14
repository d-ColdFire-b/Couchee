package com.example.application.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class ReportController {

    @GetMapping("/report")
    public String report(Map<String, Object> model){

        return "report";
    }
}
