package com.example.application.Controllers;

import com.example.application.connections.JDBCLogerAndReports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.sql.SQLException;

@Controller
public class GreetingController {
    @Autowired
    JDBCLogerAndReports log;

    @GetMapping("/index")
    public String main(String name, Model model) {

        try {
            log.newLogertext("Someone entered in the website" + log.GetDateTime());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "index";
    }

}