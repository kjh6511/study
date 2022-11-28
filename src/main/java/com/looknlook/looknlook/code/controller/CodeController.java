package com.looknlook.looknlook.code.controller;

import com.looknlook.looknlook.code.domain.entity.Code;
import com.looknlook.looknlook.code.service.CodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/code")
public class CodeController {

    private final CodeService codeService;

    @GetMapping("/type/{cdTy}")
    @ResponseBody
    public List<Code> readCodeTypeList(@PathVariable("cdTy") String cdTy)throws Exception{
        List<Code> codeList = codeService.readCodeTypeList(cdTy);
        return codeList;
    }
}
