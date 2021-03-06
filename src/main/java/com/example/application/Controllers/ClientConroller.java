package com.example.application.Controllers;

import com.example.application.connections.JDBCCLient;
import com.example.application.connections.JDBCLogerAndReports;
import com.example.application.entity.Client;
import com.example.application.form.Clientform;
import com.example.application.form.Priceform;
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
    @Autowired
    JDBCLogerAndReports log;

    @GetMapping("/clients")
    public String clients(Model model, Clientform clientform, Priceform priceform){
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
            log.newLogertext("New client have been added - " + clientform.getName() + "  At  " + log.GetDateTime());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "redirect:/clients";
    }

    @PostMapping("/changedisc")
    public String changedisc (Model model, @ModelAttribute("priceform")Priceform priceform){

        try {
            cLient.UpdDisc(priceform);
            log.newLogertext("Discount of client id: "+ priceform.getPropid() + "changed to "+ priceform.getPrice() +" at" + log.GetDateTime());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "redirect:/clients";
    }

}
