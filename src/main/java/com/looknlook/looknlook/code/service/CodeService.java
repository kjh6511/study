package com.looknlook.looknlook.code.service;

import com.looknlook.looknlook.code.domain.entity.Code;
import com.looknlook.looknlook.code.repository.CodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CodeService {

    private final CodeRepository codeRepository;

    public List<Code> readCodeTypeList(String cdTy) {
        return codeRepository.findAllByCdTy(cdTy);
    }

    public String readCodeName(String cdNum){
        return codeRepository.findByCdNum(cdNum);
    }

    public String readCodeNum(String cdNm){
        return codeRepository.findByCdNm(cdNm);
    }
}
