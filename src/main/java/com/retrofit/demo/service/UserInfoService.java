package com.retrofit.demo.service;

import com.retrofit.demo.remoteService.dao.User;
import com.retrofit.demo.remoteService.responseEntity.Result;
import com.retrofit.demo.remoteService.service.RemoteUserInfoCallService;
import com.retrofit.demo.remoteService.service.RemoteUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author puthlive
 */
@Service
public class UserInfoService {
    @Autowired
    private RemoteUserInfoService remoteUserInfoService;
    @Autowired
    private RemoteUserInfoCallService remoteUserInfoCallService;
    public User getUser(String id) {
        return remoteUserInfoService.getUser(id).getData();
    }

    public Result<Object> getUserInfo(String id) {
        return Result.builder().code(0).msg("success").data(User.builder().age(99).gender("male").id("0001").name("alex").build()).build();
    }

    public User getUser2(String id) {
        return remoteUserInfoService.getUser2(id).getData();
    }

    public User getUserByCall(String id) throws IOException {
        return remoteUserInfoCallService.getUser(id).execute().body();
    }
}
