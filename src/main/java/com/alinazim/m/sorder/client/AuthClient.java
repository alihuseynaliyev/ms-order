package com.alinazim.m.sorder.client;

import com.alinazim.m.sorder.model.client.response.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-auth", url = "http://localhost:8080/auth")
public interface AuthClient {
    @GetMapping("/verify")
    Boolean verify(@RequestParam String token);

    @GetMapping("/users")
    AuthResponse getUsers(@RequestParam String token);
}
