package com.example.application.Controllers;

import com.example.application.connections.JDBCLogerAndReports;
import com.example.application.entity.Client;
import com.example.application.entity.Master;
import com.example.application.entity.Prop;
import com.example.application.form.Clientform;
import com.example.application.form.Masterform;
import com.example.application.form.Propform;
import com.example.application.form.Typeform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ReportController {

    @Autowired
    JDBCLogerAndReports reports;

    @GetMapping("/report")
    public String report(Model model, Masterform masterform, Clientform clientform, Typeform typeform){

        return "report";
    }

    @PostMapping("/masterrep")
    public String masterrep(Model model, @ModelAttribute("masterrep") Masterform masterform){
        model.addAttribute("masterform",masterform);
        List<Prop> masterlist = new ArrayList<>();

        try {
           masterlist = reports.masterreport(masterform);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        model.addAttribute("masterlist", masterlist);
        return "report";
    }
    @PostMapping("/clienproptrep")
    public String clietnproprep(Model model, @ModelAttribute("clientrep") Clientform clientform){
        model.addAttribute("clientform",clientform);
        List<Prop> clientlist = new ArrayList<>();

        try {
          clientlist = reports.clientpropreport(clientform);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        model.addAttribute("clientlist", clientlist);
        return "redirect:/report";
    }
    @PostMapping("/proptyperep")
    public String proptyperep(Model model, @ModelAttribute("masterrep") Typeform typeform){
        model.addAttribute("typeform",typeform);
        List<Prop> propList = new ArrayList<>();

        try {
            propList = reports.propsreport(typeform);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        model.addAttribute("proplist", propList);
        return "redirect:/report";
    }

}
