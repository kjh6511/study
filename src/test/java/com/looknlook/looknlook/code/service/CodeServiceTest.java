package com.looknlook.looknlook.code.service;

import com.looknlook.looknlook.code.domain.entity.Code;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CodeServiceTest {

    @Autowired
    private CodeService codeService;

    @Test
    void readCodeTypeList(){
        List<Code> codeList = codeService.readCodeTypeList("02");
        System.out.println(codeList);
    }

    @Test
    void readCodeName(){
        String result = codeService.readCodeName("01001");
        System.out.println(result);
    }
}