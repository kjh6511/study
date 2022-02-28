package com.masjjim.util.security.jwt.filter.provider;


import com.masjjim.config.Properties;
import com.masjjim.member.domain.response.ResMember;
import com.masjjim.util.security.domain.CustomUser;
import com.masjjim.util.security.jwt.constants.SecurityConstants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final Properties properties;

    //3-2. 토큰 생성
    public String createToken(HttpServletRequest request, long memNo, String memId, List<String> roles, long time) {
        byte[] signingKey = getSigningKey();

        String token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("type", SecurityConstants.TOKEN_TYPE)
                .setExpiration(new Date(System.currentTimeMillis() + time)) //24h
                .claim("memNo", memNo)
                .claim("memId", memId)
                .claim("role", roles)
                .compact();
        return token;
    }


    // 유효한 토큰인지 검사
    public UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) throws IOException {
        if (isNotEmpty(tokenHeader)) {
            try {
                byte[] signingKey = getSigningKey();

                Jws<Claims> parsedToken = Jwts.parser()
                        .setSigningKey(signingKey)
                        .parseClaimsJws(tokenHeader.replace("Bearer ", ""));

                Claims claims = parsedToken.getBody();

                String memNo = String.valueOf(claims.get("memNo"));
                String memId = (String)claims.get("memId");

                /* 탈퇴회원 검사 */
/*                if(registerMapper.readRegister(mbrId) == null) {
                    return null;
                }*/

                List<SimpleGrantedAuthority> role = ((List<?>) claims.get("role"))
                        .stream()
                        .map(authority -> new SimpleGrantedAuthority((String) authority))
                        .collect(Collectors.toList());

                if (isNotEmpty(memId)) {
                    ResMember resMember = new ResMember();
                    resMember.setMemNo(Integer.parseInt(memNo));
                    resMember.setMemId(memId);
                    resMember.setMemPw("");

                    UserDetails userDetails = new CustomUser(resMember, role);

                    return new UsernamePasswordAuthenticationToken(userDetails, null, role);
                }
            } catch (ExpiredJwtException exception) {
                log.warn("Request to parse expired JWT : {} failed : {}", tokenHeader, exception.getMessage());
            } catch (UnsupportedJwtException exception) {
                log.warn("Request to parse unsupported JWT : {} failed : {}", tokenHeader, exception.getMessage());
//                throw new DisabledException("Request to parse unsupported");
            } catch (MalformedJwtException exception) {
                log.warn("Request to parse invalid JWT : {} failed : {}", tokenHeader, exception.getMessage());
//                throw new DisabledException("Request to parse invalid");
            } catch (IllegalArgumentException exception) {
                log.warn("Request to parse empty or null JWT : {} failed : {}", tokenHeader, exception.getMessage());
//                throw new DisabledException("Request to parse empty");
            }
        }

        return null;
    }

    private byte[] getSigningKey() {
        return properties.getSecretKey().getBytes();
    }

    private boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    private boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

}
