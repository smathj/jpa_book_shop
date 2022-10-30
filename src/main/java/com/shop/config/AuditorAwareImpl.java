package com.shop.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    // * 현재로그인한 사용자의 정보로 , 등록자, 수정자를 입력하기위해서 
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // 느낌이 시큐리티 컨텍스트에서 인증된 사용자를 가져온다
        String userId = "";
        if (authentication != null) {
            userId = authentication.getName();  // 사용자의 이름을 가져온다
        }

        return Optional.of(userId);
    }
}
