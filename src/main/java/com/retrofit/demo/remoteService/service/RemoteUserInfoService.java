package com.retrofit.demo.remoteService.service;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.github.lianjiatech.retrofit.spring.boot.degrade.Degrade;
import com.github.lianjiatech.retrofit.spring.boot.degrade.DegradeStrategy;
import com.github.lianjiatech.retrofit.spring.boot.retry.Retry;
import com.retrofit.demo.remoteService.dao.User;
import com.retrofit.demo.remoteService.responseEntity.Result;
import retrofit2.http.*;

import java.util.Map;

/**
 * @author puthlive
 */
@RetrofitClient(baseUrl = "${user.info.url}")
public interface RemoteUserInfoService {
    @GET(value = "getUserInfo")
    Result<User> getUserGET(@Query("id") String id);

    @POST(value = "getUserInfo")
    Result<User> getUserPOST(@Query("id") String id);

    @POST(value = "getUserPOSTField")
    Result<User> getUserPOSTField(@Field("id") String id);

    @POST(value = "getUserPOSTFieldMap")
    Result<User> getUserPOSTFieldMap(@FieldMap Map<String, Object> map);

    @DELETE(value = "deleteUser/{id}")
    Result<Object> deleteUser(@Path("id") String id);

    @POST(value = "getUserInfoRetryFail")
    Result<User> getUserRetryFail(@Query("id") String id);

    @POST(value = "getUserInfoRetry")
    @Retry(maxRetries = 10)
    Result<User> getUserRetry(@Query("id") String id);

    @POST(value = "getUserInfo")
    Result<User> getUserNeedHeaders(@HeaderMap Map<String, String> headParams, @Query("id") String id);

    @POST(value = "getUserDegrade")
    @Retry(maxRetries = 10)
    @Degrade(count = 2, timeWindow = 30, degradeStrategy = DegradeStrategy.AVERAGE_RT)
    Result<User> getUserDegrade(@Query("id") String id);
}
