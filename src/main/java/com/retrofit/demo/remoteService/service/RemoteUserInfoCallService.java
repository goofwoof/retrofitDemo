package com.retrofit.demo.remoteService.service;

import com.github.lianjiatech.retrofit.spring.boot.annotation.Intercept;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.retrofit.demo.remoteService.dao.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author puthlive
 */
@RetrofitClient(baseUrl = "${user.info.url}")
@Intercept(handler = CookieInterceptor.class, include = {"/api/**"}, exclude = "/api/test/savePerson")
public interface RemoteUserInfoCallService {
    @GET(value = "/getUserInfo")
    Call<User> getUser(@Query("id") String id);
}
