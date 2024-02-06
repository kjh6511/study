package com.reviewer.reviewer.user.domain.Response;

import com.reviewer.reviewer.user.domain.entity.User;
import com.reviewer.reviewer.util.code.CodeConverter;
import com.reviewer.reviewer.util.code.CodeStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter @Setter
public class ResUser {

    private Long userNo;

    private String userId;

    private String userNm;

    private CodeStatus userTy;

    private CodeStatus userSt;

    private String auth;

    public ResUser(User user){
        this.userNo = user.getUserNo();
        this.userId = user.getUserId();
        this.userNm = user.getUserNm();
        this.userTy = user.getUserTy();
        this.userSt = user.getUserSt();
    }
}
