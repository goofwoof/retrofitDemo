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
public class UserInfoAPI {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/getUserInfo", method = {RequestMethod.POST, RequestMethod.GET})
    public Result<Object> getUserInfo(@RequestParam String id) {
        return userInfoService.getUserInfo(id);
    }


}
