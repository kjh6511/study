package com.mealgo.ai.service;

import com.mealgo.ai.domain.dto.AIMealRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class OpenAiServiceTest {

    @Autowired
    private OpenAiService openAiService;

    @Test
    void getMealRecommendation() throws Exception {

        AIMealRequest aiMealRequest = new AIMealRequest();
        aiMealRequest.setAge(30);
        aiMealRequest.setGender("여성");
        aiMealRequest.setHeight(166);
        aiMealRequest.setWeight(45);
        aiMealRequest.setGoal("건강한 체력");
        aiMealRequest.setAllergy("없음");
        aiMealRequest.setLike("과일");

        String ai = openAiService.getMealRecommendation(aiMealRequest);

        System.out.println(ai);

    }
}