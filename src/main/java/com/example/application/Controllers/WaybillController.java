package com.example.application.Controllers;

import com.example.application.connections.JDBCCart;
import com.example.application.connections.JDBCWaybill;
import com.example.application.entity.Cart;
import com.example.application.entity.Waybill;
import com.example.application.form.Cartform;
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

    @GetMapping("/waybills")
    public String waybills(Model model, Waybillform waybillform, Cartform cartform){
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

        model.addAttribute("waybillform", waybillform);

        try {
            waybill.newWaybill(waybillform);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return "redirect:/waybills";
    }

    @PostMapping("/addcartmem")
    public String addcartmem(Model model, @ModelAttribute("cartform") Cartform cartform){

        model.addAttribute("cartform", cartform);

        try {
            cart.addCartmember(cartform);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "redirect:/waybills";
    }

    @PostMapping("/getdetails")
    public String getdetails(Model model, @ModelAttribute("idform") Cartform cartform) {
        List<Cart> cartlist = new ArrayList<>();
        List<Waybill> waybilllis = new ArrayList<>();

        model.addAttribute("idform", cartform);

        try {
          //cartlist = cart.getCart(cartform);
          waybilllis = waybill.getOnewaybill(cartform);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        model.addAttribute("list", cartlist);
        model.addAttribute("watibilinf", waybilllis);

        return "redirect:/waybills";
    }
}
