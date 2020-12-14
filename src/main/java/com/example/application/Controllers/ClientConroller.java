package com.example.application.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class ClientConroller {

    @GetMapping("/clients")
    public String clients(Map<String ,Object> model){

        return "clients";
    }

}
