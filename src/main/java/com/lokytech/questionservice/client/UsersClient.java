package com.lokytech.questionservice.client;

import com.lokytech.questionservice.dto.UsersDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user-service", url="localhost:8000")
public interface UsersClient {

    @GetMapping("/users/{id}")
    UsersDTO getUserById(@PathVariable("id") Long id);
}
