package com.mealgo.common.utils;

import com.mealgo.common.code.CodeValue;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * Spring MVC에서 HTTP 요청 파라미터(String)를
 * CodeValue 기반 Enum으로 자동 변환해주는 공통 ConverterFactory.
 *
 * 예: HTML form에서 value="02002"로 넘어온 값을 ShopEnum.CLOSED 등으로 자동 변환.
 * 모든 Enum이 CodeValue 인터페이스를 구현하고 있으면 이 하나로 커버 가능.
 */
@Component
public class EnumCodeConverterFactory implements ConverterFactory<String, CodeValue> {

    /**
     * 요청 파라미터 바인딩 시 사용할 Converter를 반환
     *
     * @param targetType 변환 대상 Enum 클래스 (예: ShopEnum.class)
     * @param <T> CodeValue를 구현한 Enum 타입
     * @return 문자열을 Enum으로 바꾸는 Converter
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends CodeValue> Converter<String, T> getConverter(Class<T> targetType) {
        // Enum<T> 타입으로 확정 불가능하므로 Class<T>를 안전하게 캐스팅
        return new StringToEnumCodeConverter<>((Class<? extends Enum<?>>) targetType);
    }

    /**
     * 실제 String → Enum 변환 로직을 수행하는 내부 클래스
     *
     * @param <T> CodeValue 타입의 Enum
     */
    private static class StringToEnumCodeConverter<T extends CodeValue> implements Converter<String, T> {

        private final Class<? extends Enum<?>> enumType;

        public StringToEnumCodeConverter(Class<? extends Enum<?>> enumType) {
            this.enumType = enumType;
        }

        /**
         * 문자열(source)을 해당 Enum 상수로 변환
         *
         * @param source HTML 폼에서 전달된 code 문자열 (예: "02001")
         * @return 일치하는 Enum 상수 (예: ShopEnum.OPEN)
         */
        @SuppressWarnings("unchecked")
        @Override
        public T convert(String source) {
            if (source == null || source.isEmpty()) return null;

            Object[] constants = enumType.getEnumConstants();

            for (Object constant : constants) {
                if (!(constant instanceof CodeValue)) continue;

                CodeValue codeValue = (CodeValue) constant;

                if (codeValue.getCode().equals(source)) {
                    return (T) codeValue;
                }
            }

            throw new IllegalArgumentException(
                    "코드 [" + source + "] 를 " + enumType.getSimpleName() + " 로 변환할 수 없습니다."
            );
        }
    }
}



