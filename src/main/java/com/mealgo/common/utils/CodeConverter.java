package com.mealgo.common.utils;

import com.mealgo.common.code.CodeValue;
import jakarta.persistence.AttributeConverter;

/**
 * JPA의 AttributeConverter를 구현한 추상 클래스.
 * Enum ↔ DB 컬럼(String) 변환을 공통화한 기본 구조를 제공합니다.
 *
 * 사용 예:
 * - DB에는 "02001" 같은 code만 저장
 * - 애플리케이션에서는 ShopEnum.OPEN 등 Enum 타입으로 사용
 *
 * 모든 Enum이 CodeValue 인터페이스를 구현하고 있으면 이 추상 클래스를 상속해서 재사용 가능합니다.
 *
 * @param <E> CodeValue를 구현한 Enum 타입
 */
public abstract class CodeConverter<E extends Enum<E> & CodeValue> implements AttributeConverter<E, String> {

    /**
     * 변환 대상 Enum 클래스
     */
    private final Class<E> enumClass;

    /**
     * 서브 클래스에서 enumClass를 전달받기 위한 생성자
     *
     * @param enumClass 변환할 Enum 클래스 (예: ShopEnum.class)
     */
    protected CodeConverter(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    /**
     * Enum → DB column(String) 변환
     *
     * @param attribute Enum 값 (예: ShopEnum.OPEN)
     * @return DB에 저장할 code 값 (예: "02001")
     */
    @Override
    public String convertToDatabaseColumn(E attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    /**
     * DB column(String) → Enum 변환
     *
     * @param dbData DB에 저장된 문자열 코드 (예: "02001")
     * @return Enum 값 (예: ShopEnum.OPEN)
     */
    @Override
    public E convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return EnumCodeUtils.fromCode(enumClass, dbData);
    }
}

