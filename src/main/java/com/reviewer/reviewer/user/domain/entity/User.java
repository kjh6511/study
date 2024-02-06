package com.reviewer.reviewer.user.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reviewer.reviewer.util.code.CodeConverter;
import com.reviewer.reviewer.util.code.CodeStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "User")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    private String userId;

    @JsonIgnore
    private String userPw;

    private String userNm;

    @Convert(converter = CodeConverter.class)
    @Column(name = "user_ty")
    private CodeStatus userTy;

    @Convert(converter = CodeConverter.class)
    @Column(name = "user_st")
    private CodeStatus userSt;

    private String auth;


}
