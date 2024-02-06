package com.reviewer.reviewer.user.service;

import com.reviewer.reviewer.user.domain.Request.ReqUser;
import com.reviewer.reviewer.user.domain.Response.ResUser;
import com.reviewer.reviewer.user.domain.entity.User;
import com.reviewer.reviewer.user.repository.UserRepository;
import com.reviewer.reviewer.util.code.CodeStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public String checkUserId(String userId){
        Optional<User> member = userRepository.findByUserId(userId);
        String result = "사용가능";
        if(member.isPresent()){
            result ="ID 중복";
        }
        return result;
    }

    public Long createUser(ReqUser reqUser) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String userPw = passwordEncoder.encode(reqUser.getUserPw());

        User user = User.builder()
                .userId(reqUser.getUserId())
                .userNm(reqUser.getUserNm())
                .userPw(userPw)
                .userTy(CodeStatus.ADMIN)
                .userSt(CodeStatus.OK)
                .auth("ROLE_USER")
                .build();
        userRepository.save(user);
        return user.getUserNo();
    }

    public ResUser readUser(Long userNo) {
        return new ResUser(userRepository.findById(userNo).orElse(null));
    }
}
