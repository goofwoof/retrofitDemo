package com.retrofit.demo.service;

import com.retrofit.demo.remoteService.dao.User;
import com.retrofit.demo.remoteService.responseEntity.Result;
import com.retrofit.demo.remoteService.service.RemoteUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    private RemoteUserInfoService userInfoService;
    public User getUser(String id) {
        return userInfoService.getUser(id).getData();
    }

    public Result<Object> getUserInfo(String id) {
        return Result.builder().code(0).msg("success").data(User.builder().age(99).gender("male").id("0001").name("alex").build()).build();
    }

    public User getUser2(String id) {
        return userInfoService.getUser2(id).getData();
    }
}
