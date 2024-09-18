package com.yash.order.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yash.order.dtos.UserDTO;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/api/users/{email}")
    UserDTO getUserByEmail(@PathVariable("email") String userId);
}

