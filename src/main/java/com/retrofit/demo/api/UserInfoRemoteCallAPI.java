package com.retrofit.demo.api;


import com.retrofit.demo.remoteService.dao.User;
import com.retrofit.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author puthlive
 */
@RestController
public class UserInfoRemoteCallAPI {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/getUserInfoCall")
    public User getUser(@RequestParam String id) throws IOException {
        return userInfoService.getUserByCall(id);
    }
}
