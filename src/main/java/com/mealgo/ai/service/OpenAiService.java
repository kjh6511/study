package com.mealgo.ai.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mealgo.ai.domain.dto.AIMealRequest;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class OpenAiService {

    @Value("${openai.api-key}")
    private String openApiKey;

    public String getMealRecommendation(AIMealRequest req) throws Exception {
        String prompt = String.format(
                """
                당신은 전문 영양사이자 운동 트레이너입니다. 사용자의 건강한 삶을 위해 하루 식단과 운동, 장보기 리스트, 동기부여 메시지를 제공합니다.
            
                사용자 정보는 다음과 같습니다:
                - 나이: %d세
                - 성별: %s
                - 키: %dcm
                - 몸무게: %dkg
                - 목표: %s
                - 좋아하는 음식: %s
                - 피해야 할 음식(알러지 포함): %s
            
                아래 정보를 포함한 JSON 형식으로 응답해 주세요:
            
                1. "content": 아침/점심/저녁/간식으로 나눠서 식단을 제안해 주세요. 각 식사마다 간단한 이유와 추천 이유를 1~2줄 덧붙여 주세요. 예: "아침: 오트밀 + 바나나 (간단하게 에너지를 공급해주고 포만감이 오래 갑니다)"
            
                2. "workout": 하루 20~30분 내외의 운동 루틴을 아침과 저녁으로 나눠 제시해 주세요. 운동 초보자를 고려해 쉬운 운동 위주로, 집에서 할 수 있도록 구성해 주세요. 각 운동에는 간단한 설명을 붙여 주세요.
            
                3. "shoppingList": 오늘 식단에 필요한 장보기 재료를 목록으로 주세요. 중복 없이 정리해주세요.
            
                4. "youtube": 식단, 운동, 요리에 참고할 만한 유튜브 검색 키워드 3개를 추천해주세요.
            
                5. "message": 사용자가 오늘 하루 힘내서 식단과 운동을 실천할 수 있도록 진심 어린 응원 메시지를 한 문장으로 작성해 주세요.
            
                응답 예시:
                {
                  "content": "...",
                  "workout": "...",
                  "shoppingList": ["오트밀", "닭가슴살", "브로콜리"],
                  "youtube": ["홈트 루틴", "건강한 다이어트 식단", "닭가슴살 요리"],
                  "message": "작은 실천이 큰 변화를 만듭니다. 오늘도 잘해내고 있어요!"
                }
            
                반드시 JSON 형식으로만 응답해 주세요.
                """,
                req.getAge(), req.getGender(), req.getHeight(), req.getWeight(),
                req.getGoal(), req.getLike(), req.getAllergy()
        );

        return askAi(prompt);
    }

    public String askAi(String prompt) throws Exception {
        String apiKey = openApiKey;
        String endpoint = "https://api.openai.com/v1/chat/completions";

        OkHttpClient client = new OkHttpClient();

        JsonObject message = new JsonObject();
        message.addProperty("role", "user");
        message.addProperty("content", prompt);

        JsonArray messages = new JsonArray();
        messages.add(message);

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("model", "gpt-3.5-turbo");
        requestBody.add("messages", messages);

        Request request = new Request.Builder()
                .url(endpoint)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .post(RequestBody.create(
                        requestBody.toString(),
                        MediaType.parse("application/json")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();

            JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
            JsonArray choices = json.getAsJsonArray("choices");
            if (choices != null && choices.size() > 0) {
                String content = choices.get(0).getAsJsonObject()
                        .getAsJsonObject("message")
                        .get("content").getAsString();

                // GPT가 JSON 문자열로 응답했으므로 그대로 전달
                return content;
            }
            return "{\"content\": \"AI 응답을 가져올 수 없습니다.\", \"youtube\": []}";
        }
    }
}

