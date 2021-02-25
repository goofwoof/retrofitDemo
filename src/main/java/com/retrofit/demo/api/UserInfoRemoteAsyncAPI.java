package com.retrofit.demo.api;


import com.retrofit.demo.remoteService.dao.User;
import com.retrofit.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * 异步
 * @author puthlive
 */
@RestController
public class UserInfoRemoteAsyncAPI {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/getUserInfoAsync")
    public User getUserInfoAsync(@RequestParam String id) throws ExecutionException, InterruptedException {
        return userInfoService.getUserAsync(id);
    }

    @GetMapping("/getUserInfoSync")
    public User getUserInfoSync(@RequestParam String id) {
        return userInfoService.getUserSync(id);
    }
}
