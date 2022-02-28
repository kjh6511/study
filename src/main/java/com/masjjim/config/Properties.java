package com.masjjim.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("com.masjjim")
public class Properties {

    private String uploadPath;

    private String secretKey;
}
