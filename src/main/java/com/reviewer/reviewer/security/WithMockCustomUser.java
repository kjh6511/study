package com.reviewer.reviewer.security;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
//@WithSecurityContext(factory = WithMockUserSecurityContextFactory.class)
public @interface WithMockCustomUser {
}
