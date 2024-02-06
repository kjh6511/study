package com.reviewer.reviewer.user.controller;

import com.reviewer.reviewer.user.domain.Request.ReqUser;
import com.reviewer.reviewer.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exception", required = false) String exception,
                            Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "login/login";
    }

    @GetMapping("/register")
    public ModelAndView viewCreateUser()throws Exception{
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login/register");
        return mav;
    }
    
    @ResponseBody
    @GetMapping("/register/check/{userId}")
    public String checkUserId(@PathVariable("userId") String userId)throws Exception{
        String result = userService.checkUserId(userId);
        return result;
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute ReqUser reqUser)throws Exception{
        userService.createUser(reqUser);
        return "redirect:/login";
    }

}
