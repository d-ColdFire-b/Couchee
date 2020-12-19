package com.example.application.Controllers;

import com.example.application.connections.JDBCMaster;
import com.example.application.connections.JDBCType;
import com.example.application.connections.JDBCprops;
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
import java.util.List;

@Controller
public class WorkController {

    @Autowired
    JDBCMaster master;
    @Autowired
    JDBCprops prop;
    @Autowired
    JDBCType type;

    @GetMapping("/work")
    public String work (Model model, Masterform masterform, Typeform typeform, Propform propform){
/*
        model.addAttribute("masterlist", masterList);
        model.addAttribute("proplist", propList);
        model.addAttribute("typelist", typeList);
        try {
            propList = prop.getProps();
            masterList = master.getMasters();
            typeList = type.getalltypes();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



*/

        return "work";
    }

    @PostMapping("/addmaster")
    public String addmaster(Model model, @ModelAttribute("masterform") Masterform masterform){
        model.addAttribute("masterform", masterform);
        try {
            master.newmaster(masterform);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "work";
    }

    @PostMapping("/addprop")
    public String addprop (Model model, @ModelAttribute("propform") Propform propform){
    model.addAttribute("propform", propform);
        try {
            prop.newprop(propform);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "work";
    }
    @PostMapping("/addtype")
    public String addtype(Model model, @ModelAttribute("typeform") Typeform typeform){
        model.addAttribute("typeform",typeform);
        try {
            type.newtype(typeform);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "work";
    }
}
