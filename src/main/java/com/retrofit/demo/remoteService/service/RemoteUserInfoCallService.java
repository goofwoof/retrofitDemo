package com.retrofit.demo.remoteService.service;

import com.github.lianjiatech.retrofit.spring.boot.annotation.Intercept;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.retrofit.demo.remoteService.dao.User;
import com.retrofit.demo.remoteService.interceptor.HeadersInterceptor;
import com.retrofit.demo.remoteService.responseEntity.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author puthlive
 */
@RetrofitClient(baseUrl = "${user.info.url}")
@Intercept(handler = HeadersInterceptor.class)
public interface RemoteUserInfoCallService {
    @GET(value = "getUserInfo")
    Call<Result<User>> getUser(@Query("id") String id);
}
