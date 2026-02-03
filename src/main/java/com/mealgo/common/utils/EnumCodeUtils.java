package com.mealgo.common.utils;

import com.mealgo.common.code.CodeValue;

import java.util.Arrays;

/**
 * CodeValue 인터페이스를 구현한 Enum 타입에서
 * code 값으로 Enum 상수를 찾거나, value 문자열을 가져오는 유틸리티 클래스입니다.
 *
 * 이 유틸은 Spring MVC, DB 저장/조회, JSON 변환 등 여러 곳에서 재사용 가능합니다.
 */
public class EnumCodeUtils {

    /**
     * 주어진 code 값에 해당하는 Enum 상수를 찾아 반환합니다.
     *
     * @param enumType Enum 클래스 타입 (예: ShopEnum.class)
     * @param code     매칭할 code 값 (예: "02001")
     * @param <T>      Enum 타입이며 CodeValue를 구현한 타입
     * @return         일치하는 Enum 상수 (예: ShopEnum.OPEN)
     * @throws         IllegalArgumentException 매칭되는 값이 없을 경우 예외 발생
     */
    public static <T extends Enum<T> & CodeValue> T fromCode(Class<T> enumType, String code) {
        return Arrays.stream(enumType.getEnumConstants())
                .filter(e -> e.getCode().equals(code))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("[" + enumType.getSimpleName() + "] 코드 없음: " + code));
    }

    /**
     * 주어진 code 값에 해당하는 Enum 상수의 value(설명값)를 반환합니다.
     * (예: "02001" → "사용")
     *
     * @param enumType Enum 클래스 타입
     * @param code     매칭할 code 값
     * @param <T>      Enum 타입이며 CodeValue를 구현한 타입
     * @return         해당 code의 value (설명값), 없으면 "알 수 없음"
     */
    public static <T extends Enum<T> & CodeValue> String getValueByCode(Class<T> enumType, String code) {
        return Arrays.stream(enumType.getEnumConstants())
                .filter(e -> e.getCode().equals(code))
                .map(CodeValue::getValue)
                .findFirst()
                .orElse("알 수 없음");
    }
}

