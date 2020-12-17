package com.example.application.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class WaybillController {

    @GetMapping("/waybills")
    public String waybills(Map<String,Object> model){
        return "waybills";

    }


}
