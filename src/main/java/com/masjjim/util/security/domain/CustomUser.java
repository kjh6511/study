package com.masjjim.util.security.domain;

import com.masjjim.member.domain.response.ResMember;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;


public class CustomUser extends User{

    private static final long serialVersionUID = 1L;

    //login 정보 domain
    private ResMember resMember;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    //domain에서 id,pw 가져오기
    public CustomUser(ResMember resMember) {
        super(resMember.getMemId(), resMember.getMemPw(), Collections.singleton(new SimpleGrantedAuthority(resMember.getMemAuth())));

        this.resMember = resMember;
    }

    public CustomUser(ResMember resMember, Collection<? extends GrantedAuthority> authorities) {
        super(resMember.getMemId(), resMember.getMemPw(), authorities);

        this.resMember = resMember;
    }

    public String getMemId() {
        return resMember.getMemId();
    }

    public Integer getMemNo() {
        return resMember.getMemNo();
    }
}
