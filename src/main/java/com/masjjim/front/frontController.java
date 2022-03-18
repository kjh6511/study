package com.masjjim.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front")
public class frontController {

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

    @GetMapping("/register")
    public String register(){
        return "login/register";
    }

    @GetMapping("/main")
    public String main(){
        return "main/main";
    }
}
