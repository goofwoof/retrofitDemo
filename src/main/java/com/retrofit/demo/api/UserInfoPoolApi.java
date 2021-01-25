package com.retrofit.demo.api;

import com.retrofit.demo.remoteService.dao.User;
import com.retrofit.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 线程池
 * @author puthlive
 */
@RestController
public class UserInfoPoolApi {
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/getUserInfoPool")
    public User getUserInfoPool(@RequestParam String id) {
        return userInfoService.getUserInfoPool(id);
    }
}
