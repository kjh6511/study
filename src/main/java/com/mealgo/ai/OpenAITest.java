package com.mealgo.ai;

import okhttp3.*;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Value;

public class OpenAITest {
    public static void main(String[] args) throws Exception {
        String apiKey = null;
        String endpoint = "https://api.openai.com/v1/chat/completions";

        OkHttpClient client = new OkHttpClient();

        JsonObject message = new JsonObject();
        message.addProperty("role", "user");
        message.addProperty("content", "흠 오늘은 2025년 5월 21인데...");

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
            System.out.println(responseBody);
        }
    }
}
