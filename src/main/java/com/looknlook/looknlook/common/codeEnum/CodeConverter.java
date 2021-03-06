package com.looknlook.looknlook.common.codeEnum;

import org.aspectj.apache.bcel.classfile.Code;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class CodeConverter implements AttributeConverter<CodeStatus, String> {

    @Override
    public String convertToDatabaseColumn(CodeStatus attribute) {
        return attribute.getCode();
    }

    @Override
    public CodeStatus convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(CodeStatus.class).stream()
                .filter(codeStatus -> codeStatus.getCode().equals(dbData))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
