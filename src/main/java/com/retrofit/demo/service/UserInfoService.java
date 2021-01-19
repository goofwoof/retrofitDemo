package com.retrofit.demo.service;

import com.retrofit.demo.remoteService.dao.User;
import com.retrofit.demo.remoteService.service.RemoteUserInfoCallService;
import com.retrofit.demo.remoteService.service.RemoteUserInfoPoolService;
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
    @Autowired
    private RemoteUserInfoPoolService remoteUserInfoPoolService;


    public User getUserGET(String id) {
        return remoteUserInfoService.getUserGET(id).getData();
    }

    public User getUserPOST(String id) {
        return remoteUserInfoService.getUserPOST(id).getData();
    }

    public User getUserRetryFail(String id) {
        return remoteUserInfoService.getUserRetryFail(id).getData();
    }

    public User getUserRetry(String id) {
        return remoteUserInfoService.getUserRetry(id).getData();
    }

    public User getUserInfoPool(String id) {
        return remoteUserInfoPoolService.getUserInfoPool(id).getData();
    }

    public User getUserDegrade(String id) {
        return remoteUserInfoService.getUserDegrade(id).getData();
    }

    public User getUserByCall(String id) throws IOException {
        return remoteUserInfoCallService.getUser(id).execute().body().getData();
    }

    public User getUserNeedHeaders(String id) throws IOException {
        return remoteUserInfoCallService.getUser(id).execute().body().getData();
    }
}
