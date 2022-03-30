package com.masjjim.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/board-create")
    public String createBoard(Model model, HttpServletRequest request){
        Integer borCatNo = Integer.valueOf(request.getParameter("borCatNo"));
        Integer borMenuNo = Integer.valueOf(request.getParameter("borMenuNo"));
        model.addAttribute("borCatNo",borCatNo);
        model.addAttribute("borMenuNo",borMenuNo);
        return "board/board-create";
    }

    @GetMapping("/board")
    public String board(Model model, HttpServletRequest request){
        Integer borCatNo = Integer.valueOf(request.getParameter("borCatNo"));
        Integer borMenuNo = Integer.valueOf(request.getParameter("borMenuNo"));
        model.addAttribute("borCatNo",borCatNo);
        model.addAttribute("borMenuNo",borMenuNo);
        return "board/board";
    }

    @GetMapping("/store-view")
    public String readStore(Model model, HttpServletRequest request){
        Integer borCatNo = Integer.valueOf(request.getParameter("borCatNo"));
        Integer borMenuNo = Integer.valueOf(request.getParameter("borMenuNo"));
        Integer stoNo = Integer.valueOf(request.getParameter("stoNo"));
        model.addAttribute("borCatNo",borCatNo);
        model.addAttribute("borMenuNo",borMenuNo);
        model.addAttribute("stoNo",stoNo);
        return "store/store-view";
    }

    @GetMapping("/board-setting")
    public String boardSetting(Model model){
        model.addAttribute("menu","게시판 설정");
        return "setting/board-setting";
    }
}
