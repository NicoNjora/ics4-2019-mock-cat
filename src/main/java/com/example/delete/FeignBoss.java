package com.example.delete;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "testing", url = "http://localhost:8080")
public interface FeignBoss {
    @RequestMapping(method = RequestMethod.GET, value = "/movies")
    Void getStores();
}
