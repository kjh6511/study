package com.mealgo.utils.config;

import com.mealgo.common.utils.EnumCodeConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final EnumCodeConverterFactory enumCodeConverterFactory;

    public WebConfig(EnumCodeConverterFactory enumCodeConverterFactory) {
        this.enumCodeConverterFactory = enumCodeConverterFactory;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(enumCodeConverterFactory);
    }
}

