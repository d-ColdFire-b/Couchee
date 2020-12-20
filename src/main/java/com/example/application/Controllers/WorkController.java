package com.example.application.Controllers;

import com.example.application.connections.JDBCLogerAndReports;
import com.example.application.connections.JDBCMaster;
import com.example.application.connections.JDBCType;
import com.example.application.connections.JDBCprops;
import com.example.application.entity.Client;
import com.example.application.entity.Master;
import com.example.application.entity.Prop;
import com.example.application.entity.Type;
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

@Controller
public class WorkController {

    @Autowired
    JDBCMaster master;
    @Autowired
    JDBCprops prop;
    @Autowired
    JDBCType type;
    @Autowired
    JDBCLogerAndReports log;

    @GetMapping("/work")
    public String work (Model model, Masterform masterform, Typeform typeform, Propform propform){
        List<Master> masterlist = new ArrayList<>();
        List<Type> typelist = new ArrayList<>();
        List<Prop> proplist = new ArrayList<>();
        try {
            typelist = type.getalltypes();
            masterlist = master.getMasters();
            proplist = prop.getnamedProps();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        model.addAttribute("typelist",typelist);
        model.addAttribute("masterlist", masterlist);
        model.addAttribute("proplist", proplist);
        return "work";
    }

    @PostMapping("/addmaster")
    public String addmaster(Model model, @ModelAttribute("masterform") Masterform masterform){
        model.addAttribute("masterform", masterform);
        try {
            master.newmaster(masterform);
            log.newLogertext("New master have been added - "+masterform.getName() +"  At  " + log.GetDateTime());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "redirect:/work";
    }

    @PostMapping("/addprop")
    public String addprop (Model model, @ModelAttribute("propform") Propform propform){
    model.addAttribute("propform", propform);
        try {
            prop.newprop(propform);
            log.newLogertext("New prop have been added - "+propform.getName() + "  At  " + log.GetDateTime());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "redirect:/work";
    }
    @PostMapping("/addtype")
    public String addtype(Model model, @ModelAttribute("typeform") Typeform typeform){
        model.addAttribute("typeform",typeform);
        try {
            type.newtype(typeform);
            log.newLogertext("New type have been added - "+typeform.getName() + "  At  " + log.GetDateTime());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "redirect:/work";
    }
}
