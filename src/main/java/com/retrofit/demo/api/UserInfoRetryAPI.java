package com.retrofit.demo.api;

import com.retrofit.demo.remoteService.dao.User;
import com.retrofit.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 重试
 * @author puthlive
 */
@RestController
public class UserInfoRetryAPI {
    @Autowired
    private UserInfoService userInfoService;


    @PostMapping("/getUserRetryFail")
    public User getUserRetryFail(@RequestParam String id) {
        return userInfoService.getUserRetryFail(id);
    }

    @PostMapping("/getUserRetry")
    public User getUserRetry(@RequestParam String id) {
        return userInfoService.getUserRetry(id);
    }

}
