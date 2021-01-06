package com.retrofit.demo.service;

import com.retrofit.demo.remoteService.dao.User;
import com.retrofit.demo.remoteService.responseEntity.Result;
import com.retrofit.demo.remoteService.service.RemoteUserInfoCallService;
import com.retrofit.demo.remoteService.service.RemoteUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public User getUserGET(String id) {
        return remoteUserInfoService.getUserGET(id).getData();
    }

    public User getUserPOST(String id) {
        return remoteUserInfoService.getUserPOST(id).getData();
    }

    public User getUserByCall(String id) throws IOException {
        return remoteUserInfoCallService.getUser(id).execute().body().getData();
    }

    public User getUserNeedHeaders(String id) throws IOException {
        return remoteUserInfoCallService.getUser(id).execute().body().getData();
    }
}
