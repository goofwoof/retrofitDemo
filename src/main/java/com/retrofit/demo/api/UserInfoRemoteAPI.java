package com.retrofit.demo.api;

import com.retrofit.demo.remoteService.dao.User;
import com.retrofit.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author puthlive
 */
@RestController
public class UserInfoRemoteAPI {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/getUser")
    public User getUser(@RequestParam String id) {
        return userInfoService.getUser(id);
    }

    @GetMapping("/getUserPOST")
    public User getUser2(@RequestParam String id) {
        return userInfoService.getUser2(id);
    }
}
