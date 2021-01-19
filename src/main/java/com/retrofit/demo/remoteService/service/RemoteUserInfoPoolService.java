package com.retrofit.demo.remoteService.service;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.retrofit.demo.remoteService.dao.User;
import com.retrofit.demo.remoteService.responseEntity.Result;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author puthlive
 */
@RetrofitClient(baseUrl = "${user.info.url}", poolName = "userInfo")
public interface RemoteUserInfoPoolService {
    @POST(value = "getUserInfo")
    Result<User> getUserInfoPool(@Query("id") String id);
}
