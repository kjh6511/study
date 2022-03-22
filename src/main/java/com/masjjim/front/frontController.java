package com.masjjim.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String main(Model model){
        model.addAttribute("menu","Home");
        return "main/main";
    }

    @GetMapping("/map")
    public String map(Model model){
        model.addAttribute("menu","Map");
        return "map/map";
    }

    @GetMapping("/board-setting")
    public String boardSetting(Model model){
        model.addAttribute("menu","게시판 설정");
        return "setting/board-setting";
    }
}
