package com.retrofit.demo.api;

import com.retrofit.demo.remoteService.dao.User;
import com.retrofit.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserInfoAPI {
    @Autowired
    private UserInfoService userInfoService;

    @POST
    public User getUserInfo(String id) {
        return userInfoService.getUserInfo(id);
    }
}
