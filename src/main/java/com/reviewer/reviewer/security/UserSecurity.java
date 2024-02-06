package com.reviewer.reviewer.security;

import com.reviewer.reviewer.user.domain.entity.User;
import com.reviewer.reviewer.util.code.CodeConverter;
import com.reviewer.reviewer.util.code.CodeStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
public class UserSecurity implements UserDetails {

    private User user;

    public UserSecurity(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> user.getAuth().toString());
        return collection;
    }

    @Override
    public String getPassword() {
        return user.getUserPw();
    }

    @Override
    public String getUsername() {
        return user.getUserId();
    }

    //true로 변경
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


//    public UserSecurity(com.reviewer.reviewer.user.domain.entity.User user, List<GrantedAuthority> authorities){
//        super(user.getUserId(), user.getUserPw(), authorities);
//        this.userNo = user.getUserNo();
//        this.userId = user.getUserId();
//        this.userNm = user.getUserNm();
//        this.userTy = user.getUserTy();
//        this.auth = user.getAuth();
//    }


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<GrantedAuthority> roles = new HashSet<>();
//        for(String role : auth.split(",")){
//            roles.add(new SimpleGrantedAuthority(role));
//        }
//        return roles;
//    }

}
