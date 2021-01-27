package com.retrofit.demo.remoteService.service;

import com.github.lianjiatech.retrofit.spring.boot.annotation.Intercept;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.retrofit.demo.remoteService.dao.User;
import com.retrofit.demo.remoteService.interceptor.HeadersInterceptor;
import com.retrofit.demo.remoteService.responseEntity.Result;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

/**
 * @author puthlive
 */
@RetrofitClient(baseUrl = "${user.info.url}")
@Intercept(handler = HeadersInterceptor.class)
public interface RemoteUserInfoCallService {
    @GET(value = "getUserInfo")
    Call<Result<User>> getUserByCall(@Query("id") String id);

    /**
     * 信息确保不要重复
     * @param headParams Headers参数
     * @param headerParam Header参数
     * @param id 用户Id
     * @return 结果
     */
    @POST(value = "getUserInfo")
    @Headers({"X-Foo: Bar", "XX-Foo: Barr"})
    Result<User> getUserNeedHeaders(@HeaderMap Map<String, String> headParams, @Header("XXX-Foo") String headerParam, @Query("id") String id);
}
