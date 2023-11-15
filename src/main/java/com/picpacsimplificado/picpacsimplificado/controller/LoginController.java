package com.picpacsimplificado.picpacsimplificado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String Form() {
        return "login/Form";

    }

}