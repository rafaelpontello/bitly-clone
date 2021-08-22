package com.rpontello.clones.bitly.config.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles implements GrantedAuthority {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_PREMIUM = "ROLE_PREMIUM";
    public static final String ROLE_FREE = "ROLE_FREE";

    private String authority;
}
