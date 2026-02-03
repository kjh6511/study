package com.mealgo.utils.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogUtil {

    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static void logJson(Object obj) {
        try {
            String json = mapper.writeValueAsString(obj);
            log.info("\n{}", json);
        } catch (Exception e) {
            log.error("JSON 변환 실패: {}", e.getMessage());
        }
    }

    public static void logPlain(Object obj) {
        log.info("객체 출력: {}", obj);
    }
}

