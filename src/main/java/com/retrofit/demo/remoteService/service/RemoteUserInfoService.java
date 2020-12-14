package com.retrofit.demo.remoteService.service;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.retrofit.demo.remoteService.dao.User;
import com.retrofit.demo.remoteService.responseEntity.Result;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


@RetrofitClient(baseUrl = "${user.info.url}")
public interface RemoteUserInfoService {
    @GET(value = "/getUserInfo")
    Result<User> getUser(@Query("id") String id);

    @POST(value = "/getUserInfo2")
    Result<User> getUser2(@Query("id") String id);
}
