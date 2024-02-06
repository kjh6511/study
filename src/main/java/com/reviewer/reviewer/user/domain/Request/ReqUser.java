package com.reviewer.reviewer.user.domain.Request;

import com.reviewer.reviewer.util.code.CodeStatus;
import lombok.*;

@Getter
@Setter
@ToString
public class ReqUser {

    private String userId;

    private String userPw;

    private String userNm;

}
