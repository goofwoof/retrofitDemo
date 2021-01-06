package com.retrofit.demo.api;

import com.retrofit.demo.remoteService.dao.User;
import com.retrofit.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UserInfoHeadersAPI {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/getUserInfoCallWithHeaders")
    public User getUser(@RequestParam String id) throws IOException {
        return userInfoService.getUserNeedHeaders(id);
    }
}
