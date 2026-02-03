package com.mealgo.ai.controller;

import com.mealgo.ai.domain.dto.AIMealRequest;
import com.mealgo.ai.service.OpenAiService;
import com.mealgo.cart.domain.dto.RequestCart;
import com.mealgo.meal.domain.entity.Meal;
import com.mealgo.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiController {

    private final OpenAiService openAiService;

    @GetMapping
    public String showForm(@AuthenticationPrincipal Member auth, Model model) {
        model.addAttribute("request", new AIMealRequest());
        return "ai/ai";
    }

    @PostMapping
    @ResponseBody
    public String askAi(@RequestBody AIMealRequest request)throws Exception{
        // OpenAI 프롬프트 생성 및 응답 받아오기
        return  openAiService.getMealRecommendation(request);
    }
}
