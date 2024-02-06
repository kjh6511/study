package com.reviewer.reviewer.home.controller;

import com.reviewer.reviewer.user.domain.Request.ReqUser;
import com.reviewer.reviewer.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
    public String home() {
        return "home/home";
    }

}
