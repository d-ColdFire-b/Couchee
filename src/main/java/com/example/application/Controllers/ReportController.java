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
    public String masterrep(Model model, @ModelAttribute("masterrep") Masterform masterform, Clientform clientform, Typeform typeform){
        model.addAttribute("masterform",masterform);
        List<Prop> masterlist = new ArrayList<>();

        try {
           masterlist = reports.masterreport(masterform);
           reports.newLogertext("Requested info about products maded by master with id: " + masterform.getId() + "   At  " + reports.GetDateTime());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        model.addAttribute("masterlist", masterlist);
        return "report";
    }

    @PostMapping("/clienproptrep")
    public String clietnproprep(Model model, @ModelAttribute("clientrep") Clientform clientform, Masterform masterform,Typeform typeform){
        model.addAttribute("clientform",clientform);
        List<Prop> clientlist = new ArrayList<>();

        try {
          clientlist = reports.clientpropreport(clientform);
            reports.newLogertext("Requested info about purchases by client with id: " + clientform.getId() + "   At  " + reports.GetDateTime());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        model.addAttribute("clientlist", clientlist);
        return "report";
    }

    @PostMapping("/proptyperep")
    public String proptyperep(Model model, @ModelAttribute("masterrep") Typeform typeform, Clientform clientform, Masterform masterform){
        model.addAttribute("typeform",typeform);
        List<Prop> propList = new ArrayList<>();

        try {
            propList = reports.propsreport(typeform);
            reports.newLogertext("Requested info about all props of type with id: " + typeform.getId() + "   At " + reports.GetDateTime());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        model.addAttribute("proplist", propList);
        return "report";
    }

}
