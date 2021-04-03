package com.minimarket.minimarketapp.endpoint.controller;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/public")
public class MainController {


    @GetMapping(value = "/version/")
    public String getProduct(){
        return "Version 1.2.0";
    }

}
