package com.example.application.Controllers;

import com.example.application.connections.JDBCCLient;
import com.example.application.entity.Client;
import com.example.application.form.Clientform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ClientConroller {

    @Autowired
    JDBCCLient cLient;

    @GetMapping("/clients")
    public String clients(Model model, Clientform clientform){
        List<Client> list = new ArrayList<>();
        try {
            list = cLient.getClients();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        model.addAttribute("list", list);

        return "clients";
    }

    @PostMapping("/addclient")
    public String addclient(Model model, @ModelAttribute("clientform") Clientform clientform){
        model.addAttribute("clientform", clientform);

        try {
            cLient.newClient(clientform);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "redirect:/clients";
    }

}
