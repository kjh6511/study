package com.masjjim.util.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchFieldOperation<T> {
    // op 값의 범위
    // EQ (equal), NEQ(not equal), LT(less than), GT(greater than), LTE(less than or equal), GTE(greater than or equal);
    private String op;
    private T value;
}
