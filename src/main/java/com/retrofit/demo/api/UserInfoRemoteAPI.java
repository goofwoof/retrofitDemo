package com.retrofit.demo.api;

import com.retrofit.demo.remoteService.dao.User;
import com.retrofit.demo.remoteService.responseEntity.Result;
import com.retrofit.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author puthlive
 */
@RestController
public class UserInfoRemoteAPI {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/getUser")
    public User getUser(@RequestParam String id) {
        return userInfoService.getUserGET(id);
    }

    @PostMapping("/getUser")
    public User getUser2(@RequestParam String id) {
        return userInfoService.getUserPOST(id);
    }

    @DeleteMapping("/deleteUser")
    public Result<Object> deleteUser(@RequestParam String id) {
        return userInfoService.deleteUser(id);
    }
}
