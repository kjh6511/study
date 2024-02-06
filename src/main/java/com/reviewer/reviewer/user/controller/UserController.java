package com.reviewer.reviewer.user.controller;

import com.reviewer.reviewer.user.domain.Request.ReqUser;
import com.reviewer.reviewer.user.domain.Response.ResUser;
import com.reviewer.reviewer.user.domain.entity.User;
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
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ModelAndView readUser(@RequestParam("userNo") Long userNo)throws Exception{
        ModelAndView mav = new ModelAndView();
        ResUser user = userService.readUser(userNo);
        mav.addObject("user", user);
        mav.setViewName("user/view-user");
        return mav;
    }
}
