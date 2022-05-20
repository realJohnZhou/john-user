package com.john.user.client.clients;

import com.john.boot.common.dto.AuthUser;
import com.john.boot.common.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author john
 */
@FeignClient(value = "user-service", url = "${user.url:}")
public interface UserClient {

    /**
     * get me
     *
     * @return login information
     */
    @GetMapping("/auth/get-me")
    Result<AuthUser> getMe();
}
