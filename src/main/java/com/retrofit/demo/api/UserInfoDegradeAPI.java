package com.retrofit.demo.api;

import com.retrofit.demo.remoteService.dao.User;
import com.retrofit.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoDegradeAPI {
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/getUserDegrade")
    public User getUserDegrade(@RequestParam String id) {
        return userInfoService.getUserDegrade(id);
    }

}
