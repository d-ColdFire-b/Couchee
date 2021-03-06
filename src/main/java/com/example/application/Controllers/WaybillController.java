package com.example.application.Controllers;

import com.example.application.connections.JDBCCart;
import com.example.application.connections.JDBCLogerAndReports;
import com.example.application.connections.JDBCWaybill;
import com.example.application.entity.Cart;
import com.example.application.entity.Waybill;
import com.example.application.form.Cartform;
import com.example.application.form.Typeform;
import com.example.application.form.Waybillform;
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
public class WaybillController {

    @Autowired
    JDBCWaybill waybill;
    @Autowired
    JDBCCart cart;
    @Autowired
    JDBCLogerAndReports log;

    @GetMapping("/waybills")
    public String waybills(Model model, Waybillform waybillform, Cartform cartform, Typeform typeform){
        List<Waybill> waybilllist = new ArrayList<>();

        try {
            waybilllist = waybill.getnamedWaybills();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        model.addAttribute("waybilllist", waybilllist);
        return "waybills";

    }

    @PostMapping("/addwaybill")
    public String addwaybill(Model model, @ModelAttribute("waybillform") Waybillform waybillform) {


        try {
            waybill.newWaybill(waybillform);
            log.newLogertext("Client with id:  " + waybillform.getClientid() +"  Get waybill at  " + log.GetDateTime());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return "redirect:/waybills";
    }

    @PostMapping("/addcartmem")
    public String addcartmem(Model model, @ModelAttribute("cartform") Cartform cartform){


        try {
            cart.addCartmember(cartform);
            cart.removepropnumber(cartform);
            log.newLogertext("New item have been added in cart of waybill - " + cartform.getWaybillid() + "  Item ID:"+ cartform.getPropid() + "  At  " + log.GetDateTime());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "redirect:/waybills";
    }

    @PostMapping("/getdetails")
    public String getdetails(Model model, @ModelAttribute("idform") Cartform cartform, Waybillform waybillform, Typeform typeform) {

        List<Cart> cartlist = new ArrayList<>();
        List<Waybill> waybilllis = new ArrayList<>();

        model.addAttribute("cartform", cartform);

        try {
          cartlist = cart.getcart(cartform);
          waybilllis = waybill.getOnewaybill(cartform);
          log.newLogertext("Details of the waybill : " + cartform.getId() + " Have been requested, at  " + log.GetDateTime());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        model.addAttribute("list", cartlist);
        model.addAttribute("watibilinf", waybilllis);

        List<Waybill> waybilllist = new ArrayList<>();

        try {
            waybilllist = waybill.getnamedWaybills();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        model.addAttribute("waybilllist", waybilllist);


        return "waybills";
    }

    @PostMapping("/closeorder")
    public String closeorder(Model model, @ModelAttribute("typeform") Typeform typeform){

        model.addAttribute("typeform", typeform);
        try {
            waybill.closeorder(typeform);
            log.newLogertext("Waybill id: "+ typeform.getId() + "was closed. At "+ log.GetDateTime());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "redirect:/waybills";
    }
}
