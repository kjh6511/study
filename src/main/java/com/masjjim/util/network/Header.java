package com.masjjim.util.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {

    //통신시간
    private LocalDateTime transactionTime;

    //api 응답 코드
    private String resultCode;

    //api 부가 설명
    private String description;

    //data
    private T data;

    //OK
    public static <T> Header<T> OK() {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }

    //DATA OK
    public static <T> Header<T> OK(T data) {
        return  (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    //CREATE
    public static <T> Header<T> CREATE() {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("CREATE")
                .build();
    }

    //DATA
    public static <T> Header<T> DATA(T data) {
        return data != null
                ? (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("READ DATA")
                .data(data)
                .build()
                : (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description("DATA NULL")
                .build();
    }

    //ERROR
    public static <T> Header<T> ERROR(String description) {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();
    }

}
