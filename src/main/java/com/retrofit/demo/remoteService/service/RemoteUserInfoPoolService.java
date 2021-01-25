package com.retrofit.demo.remoteService.service;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.retrofit.demo.remoteService.dao.User;
import com.retrofit.demo.remoteService.responseEntity.Result;
import org.springframework.scheduling.annotation.Async;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.concurrent.CompletableFuture;

/**
 * @author puthlive
 */
@RetrofitClient(baseUrl = "${user.info.url}", poolName = "userInfo")
public interface RemoteUserInfoPoolService {
    @POST(value = "getUserInfo")
    Result<User> getUserInfoPool(@Query("id") String id);

    @GET(value = "getUserInfo")
    @Async
    CompletableFuture<Result<User>> getUserAsync(@Query("id") String id);
}
